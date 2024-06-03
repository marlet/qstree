package com.sabiko.qstree.model

data class MessageDto(
    val message: MessageContainer
)

data class MessageContainer(
    val add: List<AddMessage>
)

data class AddMessage(
    val id: String,
    val text: String
)
