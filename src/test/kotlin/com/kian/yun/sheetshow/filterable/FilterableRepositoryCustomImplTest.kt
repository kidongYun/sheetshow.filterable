package com.kian.yun.sheetshow.filterable

import com.kian.yun.sheetshow.filterable.helper.Dummy
import com.kian.yun.sheetshow.filterable.helper.DummyRepository
import com.kian.yun.sheetshow.filterable.helper.QDummy
import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptions
import com.kian.yun.sheetshow.filterable.queryOptions.SimpleCondition
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.inspectors.forAtLeastOne
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class FilterableRepositoryCustomImplTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var filterableRepositoryCustomImpl: FilterableRepositoryCustomImpl

    @Autowired
    private lateinit var dummyRepository: DummyRepository

    init {
        this.given("findByCondition") {
            dummyRepository.save(Dummy(1, "jack", "red"))
            dummyRepository.save(Dummy(2, "chloe", "blue"))
            dummyRepository.save(Dummy(3, "tommy", "blue"))
            dummyRepository.save(Dummy(4, "chloe", "yellow"))
            dummyRepository.save(Dummy(5, "mike", "light yellow"))
            dummyRepository.save(Dummy(6, "pike", "light red"))

            `when`("condition 이 아무런 조건에 해당하지 않을 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(
                    SimpleCondition.of("", listOf(), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("모든 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 6
                    result.forEach { it.id shouldBeIn listOf(1, 2, 3, 4, 5, 6) }
                }
            }

            `when`("name 이 'jack' 인 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("name", listOf("jack"), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("'jack' 을 이름으로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 1
                    result.forEach { it.name shouldBe "jack" }
                }
            }

            `when`("color 가 'blue' 인 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("color", listOf("blue"), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("'blue' 를 color 값으로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 2
                    result.forEach { it.color shouldBe "blue" }
                }
            }

            `when`("name 이 'jack', 'chloe' 인 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("name", listOf("jack", "chloe"), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("'jack', 'chloe' 를 name 값으로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 3
                    result.forEach { it.name shouldBeIn listOf("jack", "chloe") }
                }
            }

            `when`("color 에 'yellow' 가 포함된 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("color", listOf("yellow"), QueryOptions.LIKE), PageRequest.of(0, 10), QDummy.dummy)

                then("'yellow' 를 포함한 값을 color 로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 2
                    result.forEach { it.color shouldContain "yellow" }
                }
            }

            `when`("color 에 'yellow', 'red' 가 포함된 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("color", listOf("yellow", "red"), QueryOptions.LIKE), PageRequest.of(0, 10), QDummy.dummy)

                then("'yellow', 'red' 포함한 값을 color 로 가진 데이터만 반환되어야 한다") {
                    result.size shouldBe 4
                    result.forAtLeastOne { it.color shouldContain "yellow" }
                    result.forAtLeastOne { it.color shouldContain "red" }
                }
            }
        }
    }
}
