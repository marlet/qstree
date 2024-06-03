package com.sabiko.qstree.mvc

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@ConditionalOnClass(name = ["org.springframework.web.servlet.config.annotation.WebMvcConfigurer"])
open class QSTreeMvcConfig : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(QSTreeMvcResolver())
    }
}