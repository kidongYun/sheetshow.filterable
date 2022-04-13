package com.kian.yun.sheetshow.filterable.querydsl

import com.querydsl.core.types.Path
import java.lang.reflect.Field

interface PathAdapter {
    fun <T : Path<out Any>> create(clazz: Class<T>, field: Field) : T
}