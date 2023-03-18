package com.paxier.webfluxdemo.config

import com.paxier.webfluxdemo.dto.ErrorResponse
import com.paxier.webfluxdemo.dto.Response
import com.paxier.webfluxdemo.dto.ValidationError
import com.paxier.webfluxdemo.exception.ErrorResponseException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.lang.System.err
import java.util.function.BiFunction

@Configuration
class RouterConfig(@Autowired val requestFunction: RequestHandler) {
    @Bean
    fun serverResponseRouterFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .GET("router/square/{input}", requestFunction::squareHandler)
            .GET("router/table/{input}", requestFunction:: tableHandler)
            .POST("router/multiply") { requestFunction.multiplyHandler(it)}
            .GET("router/square/{input}/validation") { requestFunction.squareHandlerValidation(it) }
            .onError(ErrorResponseException::class.java, errorHandler)
            .build()
    }

    val errorHandler: BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> = BiFunction { error, _ ->
        val ex = error as ErrorResponseException
        val response = ErrorResponse(ex.msg,ex.errorCode, ValidationError("Out of Range", "Value provide:${ex.input}"))

        ServerResponse.badRequest().bodyValue(response)
    }
}