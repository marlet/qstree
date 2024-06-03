package com.sabiko.qstree

import org.example.com.sabiko.qstree.QSTreeBuilder
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class FormTreeBuilderTest {

    @Test
    fun `should build tree from nested indexed keys`() {
        val map = mapOf(
            "message[add][0][id]" to listOf("msg-1"),
            "message[add][0][text]" to listOf("привет"),
            "message[add][1][id]" to listOf("msg-2"),
            "message[add][1][text]" to listOf("здравствуй")
        )

        val result = QSTreeBuilder.fromMultiValueMap(map)

        val expected = mapOf(
            "message" to mapOf(
                "add" to listOf(
                    mapOf("id" to "msg-1", "text" to "привет"),
                    mapOf("id" to "msg-2", "text" to "здравствуй")
                )
            )
        )

        assertEquals(expected, result)
    }


    @Test
    fun `should handle flat structure`() {
        val map = mapOf(
            "user[id]" to listOf("42"),
            "user[name]" to listOf("Шурик")
        )

        val expected = mapOf(
            "user" to mapOf(
                "id" to "42",
                "name" to "Шурик"
            )
        )

        assertEquals(expected, QSTreeBuilder.fromMultiValueMap(map))
    }

    @Test
    fun `should support arrays at top level`() {
        val map = mapOf(
            "items[0][name]" to listOf("apple"),
            "items[0][qty]" to listOf("3"),
            "items[1][name]" to listOf("banana"),
            "items[1][qty]" to listOf("5")
        )

        val expected = mapOf(
            "items" to listOf(
                mapOf("name" to "apple", "qty" to "3"),
                mapOf("name" to "banana", "qty" to "5")
            )
        )

        assertEquals(expected, QSTreeBuilder.fromMultiValueMap(map))
    }

    @Test
    fun `should handle multiple top-level keys`() {
        val map = mapOf(
            "order[id]" to listOf("123"),
            "order[status]" to listOf("new"),
            "customer[name]" to listOf("Ivan"),
            "customer[email]" to listOf("ivan@example.com")
        )

        val expected = mapOf(
            "order" to mapOf(
                "id" to "123",
                "status" to "new"
            ),
            "customer" to mapOf(
                "name" to "Ivan",
                "email" to "ivan@example.com"
            )
        )

        assertEquals(expected, QSTreeBuilder.fromMultiValueMap(map))
    }

    @Test
    fun `should insert value into nested structure with list and map`() {
        val map = mapOf(
            "data[items][0][name]" to listOf("table"),
            "data[items][0][price]" to listOf("100"),
            "data[meta][created]" to listOf("2025-05-23")
        )

        val expected = mapOf(
            "data" to mapOf(
                "items" to listOf(
                    mapOf("name" to "table", "price" to "100")
                ),
                "meta" to mapOf(
                    "created" to "2025-05-23"
                )
            )
        )

        assertEquals(expected, QSTreeBuilder.fromMultiValueMap(map))
    }
}
