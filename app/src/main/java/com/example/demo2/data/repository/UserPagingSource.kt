package com.example.demo2.data.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demo2.data.models.User
import com.example.demo2.data.api.ApiService
import kotlinx.coroutines.delay

@ExperimentalPagingApi
class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return kotlin.runCatching {
            val nextKey = params.key ?: FIRST_PAGE_INDEX
            val time = System.currentTimeMillis()
            val response = apiService.getPage(nextKey)
            val endTime = System.currentTimeMillis()
            val rangeTime = endTime - time // thơi gian load
            // time : Thời gian load request
            val delayTime = if (rangeTime > 500) {
                0
            } else {
                500 - rangeTime
            }
            delay(delayTime)
            val dataList = response.results ?: mutableListOf()
            val isEnd = response.info?.next.isNullOrEmpty()

//            CoroutineScope(Dispatchers.Main).launch {
//                // Hiển thị cập nhật - Main Thread
//            }
//            CoroutineScope(Dispatchers.IO).launch {
//                // IO THREAD
//            }
            LoadResult.Page(
                data = dataList,
                prevKey = null,
                nextKey = if (isEnd) null else nextKey + 1
            )
        }.getOrElse {
            // APi đang load suspend . Mình đang hy vọng nó trả về cho mình 1 ModelApi , khi code = 200
            // Nếu như không trả về đúng như m huy bvong. code = 404 . Message = NotFound
            it.message?.takeIf { it.equals("HTTP 404 Not Found") }?.run {
                // Return REesult with data is Empty
                LoadResult.Page(
                    data = mutableListOf(),
                    prevKey = null,
                    nextKey = null
                )
            } ?: kotlin.run {
                // Nếu trả về bất kì lỗi nào khác, hoặc bị đứt mạng.thì return Error
                LoadResult.Error(it)
            }
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}