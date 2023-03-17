package com.paxier.webfluxdemo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RouterConfig(@Autowired val requestFunction: RequestHandler) {
    @Bean
    fun serverResponseRouterFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .GET("router/square/{input}", requestFunction::squareHandler)
            .GET("router/table/{input}", requestFunction:: tableHandler)
            .POST("router/multiply") { requestFunction.multiplyHandler(it)}
            .build()
    }
}