package com.kian.yun.sheetshow.filterable.queryOptions

import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringPath

class DefaultQueryOptionProvider : QueryOptionProvider {
    override fun query(values: List<String>, path: Path<out Any>): BooleanExpression
    = (path as StringPath).`in`(values)
}