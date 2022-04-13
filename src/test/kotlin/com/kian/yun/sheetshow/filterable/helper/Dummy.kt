package com.kian.yun.sheetshow.filterable.helper

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Dummy(
    @Id
    val id : Long,
    val name: String,
    val color: String,
)