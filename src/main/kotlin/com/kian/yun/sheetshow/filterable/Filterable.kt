package com.kian.yun.sheetshow.filterable

interface Filterable {
    fun getConditions(): List<Condition>

    fun getConditionOption() : ConditionOption
}