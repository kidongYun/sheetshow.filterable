package com.kian.yun.sheetshow.filterable.queryOptions

import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.StringPath

class StringQueryOptionProvider(
    private val queryOptions: QueryOptions
)  : QueryOptionProvider {
    override fun query(values: List<String>, path: Path<out Any>): BooleanExpression {
        if (QueryOptions.EQUAL == queryOptions) {
            return equal(values, path as StringPath)
        }

        if (QueryOptions.LIKE == queryOptions) {
            return like(values, path as StringPath)
        }

        return Expressions.asBoolean(true).isTrue
    }

    private fun equal(values: List<String>, path: StringPath): BooleanExpression
    = path.`in`(values)

    private fun like(values: List<String>, path: StringPath): BooleanExpression
    = values
        .map { value -> path.contains(value) }
        .reduce { acc, boolExp -> acc.or(boolExp) }
}