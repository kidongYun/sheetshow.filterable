package com.kian.yun.sheetshow.filterable

import com.querydsl.core.types.dsl.BooleanExpression

enum class ConditionOption(
    val query: (acc: BooleanExpression, boolExp: BooleanExpression) -> BooleanExpression
) {
    AND(query = { acc, boolExp -> acc.and(boolExp) }),
    OR(query = { acc, boolExp -> acc.or(boolExp) })
}