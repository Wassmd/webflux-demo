package com.paxier.webfluxdemo.service

import com.paxier.webfluxdemo.dto.MultiplyRequestDto
import com.paxier.webfluxdemo.dto.Response
import com.paxier.webfluxdemo.utils.SleepUtils
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import kotlin.collections.ArrayList

@Service
class ReactiveMathService {

    fun findSquare(input: Int): Mono<Response> {
        return Mono.just(Response(Date(), input * input))
    }

    fun multipleList(input: Int): Flux<Response> {
        return Flux.range(1, 10)
            .doOnNext { i -> SleepUtils.sleep(i) }
            .doOnNext { i -> println("Reactive maths processing time:$i") }
            .map { i ->  Response(Date(), i * input) }
    }

    fun multiply(dto: Mono<MultiplyRequestDto>): Mono<Response> {
        return dto
            .map { value -> value.firstNumber * value.secondNumber }
            .map { result -> Response(Date(), result) }
    }
}