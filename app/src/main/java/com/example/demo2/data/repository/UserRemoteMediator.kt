package com.example.demo2.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.android.codelabs.paging.db.RemoteKeys
import com.example.demo2.data.api.ApiService
import com.example.demo2.data.db.UserDatabase
import com.example.demo2.data.models.User
import com.google.gson.Gson

@ExperimentalPagingApi
class UserRemoteMediator(
    private val database: UserDatabase,
    private val apiService: ApiService
) : RemoteMediator<Int, User>() {
    private val userDao = database.userDao()
    private val remoteKeysDao = database.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, User>
    ): MediatorResult {
        return try {
            // Xác định trạng thái load
            // xác định key load
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    state.anchorPosition?.run {
                        state.closestItemToPosition(this)?.id?.run {
                            Log.d("LocationRemoteMediator", "REFRESH "+ this)
                            remoteKeysDao.getRemoteKey(this)?.nextKey?.minus(1)
                        }
                    }?:1
                }
                LoadType.PREPEND -> {
                    Log.d("PAGE", "PREPEND")
                    Log.d("LocationRemoteMediator", "PREPEND ")

                    userDao.firstUser()?.run {
                        Log.d("LocationRemoteMediator", "firstItem " + Gson().toJson(this))
                        remoteKeysDao.getRemoteKey(this.id)?.prevKey
                    }?: kotlin.run {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                }
                LoadType.APPEND -> {
                    Log.d("LocationRemoteMediator", "APPEND ")
                    Log.d("PAGE", "a")
                    // Load next key
                    // load last Item
                    val lastItem = state.pages.lastOrNull()?.data?.lastOrNull()
                    lastItem?.id?.run {
                        // next key
                        Log.d("LocationRemoteMediator", "lastItem " + Gson().toJson(this))
                        remoteKeysDao.getRemoteKey(this)?.nextKey
                    } ?: kotlin.run {
                        // load to end
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                }
            }
            Log.d("PAGE", currentPage.toString())
            Log.d("LocationRemoteMediator", "currentPage " + currentPage)
            val response = apiService.getPage(currentPage)
            val dataList = response.results?.toMutableList() ?: mutableListOf()
            val endPaging = response.results.isNullOrEmpty() // end load when list = null || empty

            Log.d("LocationRemoteMediator", "dataList " + dataList.size)
            Log.d("LocationRemoteMediator", "dataListName " + dataList.map {
                it.name
            })

            // set index for item
//            dataList.forEachIndexed { index, locationTable ->
//                //index = vị trí trong list of Page
//                locationTable.index = currentPage * 1000 + index
//            }

            // sau khi tải đc dữ liệu về;. đổ dữ liệu vào DB
            database.withTransaction { // Phiên làm việc của DB
                if (loadType == LoadType.REFRESH) {
                    // Nếu mà lần đầu tiên tải dữ liệu về . sẽ clear cache cũ đi
                    userDao.deleteAllUser()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                // insert key => RoomDB
                val keyList = dataList.map { it ->
                    RemoteKeys(
                        id = it.id,
                        prevKey = (currentPage - 1).takeIf { it > 0 }, // chỉ nhận giá trị dương >1
                        nextKey = currentPage + 1
                    )
                }
                remoteKeysDao.insertAllRemoteKeys(keyList)

                // insert dataLIst => RoomDB
                userDao.insertAll(dataList)
            }
            Log.d("LocationRemoteMediator", "endOfPaginationReached " + endPaging)

            // Thông báo paging biết, là đã kết thúc rồi hay chưa .
            // còn dữ liệu sẽ đc đổ vào DB
            MediatorResult.Success(endOfPaginationReached = endPaging)
        } catch (e: Exception) {
            Log.d("LocationRemoteMediator", "e " + e)
            return MediatorResult.Error(e)
        }
    }
}