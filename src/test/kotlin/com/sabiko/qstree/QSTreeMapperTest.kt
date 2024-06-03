package com.sabiko.qstree

import com.sabiko.qstree.model.MessageDto
import org.example.com.sabiko.qstree.QSTreeMapper

import kotlin.test.Test
import kotlin.test.assertEquals

class FormTreeMapperTest {

    @Test
    fun `should convert map to DTO via Jackson`() {
        val map = mapOf(
            "message[add][0][id]" to listOf("msg-1"),
            "message[add][0][text]" to listOf("привет"),
            "message[add][1][id]" to listOf("msg-2"),
            "message[add][1][text]" to listOf("здравствуй")
        )

        val dto = QSTreeMapper.toDto (map, MessageDto::class.java)

        assertEquals("msg-1", dto.message.add[0].id)
        assertEquals("привет", dto.message.add[0].text)
        assertEquals("msg-2", dto.message.add[1].id)
        assertEquals("здравствуй", dto.message.add[1].text)
    }

}

