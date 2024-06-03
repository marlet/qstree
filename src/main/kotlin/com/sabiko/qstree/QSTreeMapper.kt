package org.example.com.sabiko.qstree

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object QSTreeMapper {

    private val mapper = jacksonObjectMapper()

    fun toJson(tree: Map<String, Any?>): JsonNode =
        mapper.valueToTree(tree)

    fun <T> toDto(map: Map<String, List<String>>, clazz: Class<T>): T {
        val tree = QSTreeBuilder.fromMultiValueMap(map);
        val jsonNode = toJson(tree)
        return mapper.treeToValue(jsonNode, clazz)
    }
}
