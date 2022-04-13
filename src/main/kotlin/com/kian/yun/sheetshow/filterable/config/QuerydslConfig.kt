package com.kian.yun.sheetshow.filterable.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

@Configuration
class QuerydslConfig (
    val entityManager: EntityManager
) {
    @Bean
    fun jpaQueryFactory ()
    = JPAQueryFactory(entityManager)
}