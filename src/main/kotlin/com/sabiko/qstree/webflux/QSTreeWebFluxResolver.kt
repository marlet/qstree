package com.sabiko.qstree.webflux

import com.sabiko.qstree.QSTree
import org.example.com.sabiko.qstree.QSTreeMapper
import org.springframework.core.MethodParameter
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class QSTreeWebFluxResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.hasParameterAnnotation(QSTree::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any> {
        return exchange.formData.map { map ->
            QSTreeMapper.toDto(map, parameter.parameterType)
        }
    }
}