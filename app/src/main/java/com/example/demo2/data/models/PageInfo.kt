package com.example.data.models

data class PageInfo(
    var count: Int = 0,
    var pages: Int = 0,
    var next: String? = null,
    var prev: String? = null
)
