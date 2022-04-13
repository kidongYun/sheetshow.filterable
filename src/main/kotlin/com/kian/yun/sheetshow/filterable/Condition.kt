package com.kian.yun.sheetshow.filterable

import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptions

interface Condition {
    fun getTarget(): String

    fun getValues(): List<String>

    fun getQueryOptions(): QueryOptions
}