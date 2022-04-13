package com.kian.yun.sheetshow.filterable.queryOptions

import com.kian.yun.sheetshow.filterable.Condition
import com.kian.yun.sheetshow.filterable.ConditionOption
import com.kian.yun.sheetshow.filterable.Filterable

class SimpleFilterable(
    private val conditions: List<Condition>,
    private val conditionOption: ConditionOption
) : Filterable {
    companion object {
        fun of(condition: List<Condition>, conditionOption: ConditionOption)
        = SimpleFilterable(condition, conditionOption)
    }
    override fun getConditions(): List<Condition>
    = this.conditions

    override fun getConditionOption(): ConditionOption
    = this.conditionOption
}