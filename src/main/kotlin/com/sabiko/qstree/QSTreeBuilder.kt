package org.example.com.sabiko.qstree

object QSTreeBuilder {

    fun fromMultiValueMap(map: Map<String, List<String>>): Map<String, Any?> {
        val result = mutableMapOf<String, Any?>()

        for ((key, values) in map.entries) {
            for (value in values) {
                val parts = parseKey(key)
                insertIntoTree(result, parts, value)
            }
        }

        return result
    }

    private fun parseKey(key: String): List<String> {
        val regex = Regex("""\w+|\[\w*]""")
        return regex.findAll(key).map { it.value.trim('[', ']') }.toList()
    }

    private fun insertIntoTree(newTreeRoot: MutableMap<String, Any?>, keyPath: List<String>, value: String) {
        var current: Any? = newTreeRoot
        keyPath.windowed(2, 1).forEach { (key, nextKey) ->
            current = when (current) {
                is MutableMap<*, *> -> {
                    val map = current as MutableMap<String, Any?>
                    map.getOrPut(key) { createNewNode(nextKey) }
                }
                is MutableList<*> -> {
                    // TODO: other list formats, and what if indices are not in order?
                    val i = key.toIntOrNull()  ?: error("Expected index, got '$key'")
                    val list = current as MutableList<Any?>
                    if (i < list.size && list[i] != null) {
                        list[i]
                    } else {
                        val newNode = createNewNode(nextKey)
                        list.add(newNode)
                        newNode
                    }

                }
                else -> throw IllegalStateException("Invalid structure at part $keyPath '$key'")
            }

        }
        when (current) {
            is MutableMap<*, *> -> {
                (current as MutableMap<String, Any?>).getOrPut(keyPath.last()) { value }
            }
            is MutableList<*> -> {
                (current as MutableList<Any?>).add(value)
            }
            else -> throw IllegalStateException("Invalid structure as leaf at part $keyPath")
        }
    }

    private fun createNewNode(key: String) = if (key.toIntOrNull() != null || key.isBlank())
        mutableListOf<Any?>()
    else
        mutableMapOf<String, Any?>()
}
