package com.hobosoft.demoapp.model

data class Page<T>(
    val list: List<T>,
    val page: Int,
    val size: Int,
    val isLast: Boolean
)
