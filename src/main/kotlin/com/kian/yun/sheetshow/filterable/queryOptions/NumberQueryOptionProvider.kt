package com.kian.yun.sheetshow.filterable.queryOptions

import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.NumberPath

class NumberQueryOptionProvider : QueryOptionProvider {
    override fun query(values: List<String>, path: Path<out Any>): BooleanExpression
    = (path as NumberPath<Long>).`in`(values.map { it.toLong() })
}