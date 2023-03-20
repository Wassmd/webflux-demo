package com.paxier.webfluxdemo.config

import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono

class SessionFilter: ExchangeFilterFunction {
    override fun filter(request: ClientRequest, next: ExchangeFunction): Mono<ClientResponse> {
        println("Generating session token")
        val requestBuilder = ClientRequest.from(request)
        val authMethod = request.attribute("auth").map { it.toString() }.orElse("basic")

        if (authMethod == "basic") {
            requestBuilder.headers { it.setBearerAuth("some Basic tokem") }
        } else {
            requestBuilder.headers { it.setBearerAuth("some jwt token") }
        }


        return next.exchange(requestBuilder.build())
    }
}