package com.sabiko.qstree.extention

import org.example.com.sabiko.qstree.QSTreeMapper
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitFormData

suspend inline fun <reified T> ServerRequest.toFormDto(): T {
    val rawForm = this.awaitFormData().toMap()
    return QSTreeMapper.toDto(rawForm, T::class.java)
}