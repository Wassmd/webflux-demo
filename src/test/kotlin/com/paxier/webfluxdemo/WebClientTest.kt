package com.paxier.webfluxdemo

import com.paxier.webfluxdemo.dto.MultiplyRequestDto
import com.paxier.webfluxdemo.dto.Response
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

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

    @Test
    fun stepVerifierTest() {
        val responseMono = webClient
            .get()
            .uri("reactive-math/square/7")
            .retrieve()
            .bodyToMono(Response::class.java)

        StepVerifier.create(responseMono)
            .expectNextMatches { it.output == 49 }
            .verifyComplete()
    }

    @Test
    fun postTest(){
        val responseMono = webClient
            .post()
            .uri("reactive-math/multiply")
            .bodyValue(MultiplyRequestDto(7,7))
            .retrieve()
            .bodyToMono(Response::class.java)
            .doOnNext { println(it) }

        StepVerifier.create(responseMono)
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    fun headerTest(){
        val responseMono = webClient
            .post()
            .uri("reactive-math/multiply")
            .bodyValue(MultiplyRequestDto(7,7))
            .header("rd-market", "410471")
            .attribute("auth", "basic1")
            .retrieve()
            .bodyToMono(Response::class.java)
            .doOnNext { println(it) }

        StepVerifier.create(responseMono)
            .expectNextCount(1)
            .verifyComplete()
    }
}