package com.kian.yun.sheetshow.filterable

import com.kian.yun.sheetshow.filterable.util.isNotStatic
import com.kian.yun.sheetshow.filterable.util.logger
import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.lang.reflect.Field
import kotlin.io.path.Path

@Repository
class FilterableRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : FilterableRepositoryCustom {

    override fun <T, U : EntityPath<T>> findByCondition(condition: Condition, pageable: Pageable, qClass: U): List<T>
    = queryFactory.selectFrom(qClass)
            .where(whereQueryOfCondition(condition, qClass))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

    private fun <T, U : EntityPath<T>> whereQueryOfCondition(condition: Condition, qClass: U) : BooleanExpression {
        qClass::class.java.declaredFields
            .filter { isNotStatic(it) }
            .map<Field, Path<out Any>> {
                Expressions.stringPath(it.name)
            }
            .filter {
                val log = logger()
                log.info("it : {}", it.toString())
                log.info("qClass : {}", condition.getTarget())
                it.toString() == condition.getTarget()
            }
            .forEach {
                return condition.getQueryOptions()
                    .provide(qClass.type.getDeclaredField(condition.getTarget()).type)
                    .query(condition.getValues(), it)
            }

        return Expressions.asBoolean(true).isTrue
    }

    override fun <T, U : EntityPath<T>> findByFilterable(
        filterable: Filterable,
        pageable: Pageable,
        qClass: U
    ): List<T> {
        TODO("Not yet implemented")
    }
}