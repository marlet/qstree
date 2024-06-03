package com.sabiko.qstree.webflux

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer
import org.springframework.web.server.ServerWebExchange

@Configuration
@ConditionalOnClass(ServerWebExchange::class) // TODO use name
open class QSTreeWebFluxConfig : WebFluxConfigurer {
    override fun configureArgumentResolvers(config: ArgumentResolverConfigurer) {
        config.addCustomResolver(QSTreeWebFluxResolver())
    }
}
