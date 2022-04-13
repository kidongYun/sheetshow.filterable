package com.kian.yun.sheetshow.filterable.queryOptions

enum class QueryOptions {
    EQUAL,
    LIKE;

    fun provide(clazz: Class<out Any>) : QueryOptionProvider {
        if(clazz == String::class.java) {
            return StringQueryOptionProvider(this)
        }

        if(clazz == Number::class.java) {
            return NumberQueryOptionProvider()
        }

        return DefaultQueryOptionProvider()
    }
}