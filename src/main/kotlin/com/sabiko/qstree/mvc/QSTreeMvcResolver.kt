package com.sabiko.qstree.mvc

import com.sabiko.qstree.QSTree
import org.example.com.sabiko.qstree.QSTreeMapper
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class QSTreeMvcResolver : HandlerMethodArgumentResolver {
        override fun supportsParameter(parameter: MethodParameter): Boolean =
            parameter.hasParameterAnnotation(QSTree::class.java)

        override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer,
            webRequest: NativeWebRequest,
            binderFactory: WebDataBinderFactory?
        ): Any {
            val parameterMap = webRequest.parameterMap.mapValues { it.value.toList() }
            return QSTreeMapper.toDto(parameterMap, parameter.parameterType)
        }
    }
