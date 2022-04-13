package com.kian.yun.sheetshow.filterable.helper

import org.springframework.data.jpa.repository.JpaRepository

interface DummyRepository : JpaRepository<Dummy, Long> {
}