package com.kian.yun.sheetshow.filterable.querydsl

import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.Expressions
import java.lang.reflect.Field

enum class PathAdapterImpl : PathAdapter {
    StringPathFactory {
        override fun <T : Path<out Any>> create(clazz: Class<T>, field: Field): T
        = Expressions.stringPath(field.name)
    }
}