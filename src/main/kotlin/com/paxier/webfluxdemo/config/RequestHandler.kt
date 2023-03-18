package com.paxier.webfluxdemo.config

import com.paxier.webfluxdemo.dto.MultiplyRequestDto
import com.paxier.webfluxdemo.dto.Response
import com.paxier.webfluxdemo.exception.ErrorResponseException
import com.paxier.webfluxdemo.service.ReactiveMathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class RequestHandler(@Autowired val service: ReactiveMathService) {

    fun squareHandler(request: ServerRequest): Mono<ServerResponse> {
        val input = request.pathVariable("input").toInt()

        return ServerResponse.ok().body(service.findSquare(input), Response::class.java)
    }

    fun tableHandler(request: ServerRequest): Mono<ServerResponse> {
        val input = request.pathVariable("input").toInt()

        return ServerResponse.ok().body(service.multipleList(input), Response::class.java)
    }

    fun multiplyHandler(request: ServerRequest): Mono<ServerResponse> {
        val body = request.bodyToMono(MultiplyRequestDto::class.java)

        return ServerResponse.ok().body(service.multiply(body), Response::class.java)
    }

    fun squareHandlerValidation(request: ServerRequest): Mono<ServerResponse> {
        val input = request.pathVariable("input").toInt()

        if (input < 0) {
            return Mono.error(ErrorResponseException("Invalid range", 400, input))
        }

        return ServerResponse.ok().body(service.findSquare(input), Response::class.java)
    }
}
