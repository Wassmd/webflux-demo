package com.paxier.webfluxdemo

import com.paxier.webfluxdemo.dto.Response
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClient

class WebClientTest(@Autowired val webClient: WebClient): BaseTest() {

    @Test
    fun blockTest() {
        val response = webClient
            .get()
            .uri("reactive-math/square/7")
            .retrieve()
            .bodyToMono(Response::class.java)
            .block()

        println(response)
    }
}