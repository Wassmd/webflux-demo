package com.paxier.webfluxdemo.controller

import com.paxier.webfluxdemo.dto.Response
import com.paxier.webfluxdemo.exception.ErrorResponseException
import com.paxier.webfluxdemo.service.MathService
import com.paxier.webfluxdemo.service.ReactiveMathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("reactive-maths")
class ReactiveMathsValidationController(@Autowired val service: ReactiveMathService) {

    @GetMapping("square/{input}/throw")
    fun getSquare(@PathVariable input: Int): Mono<Response> {
        if (input < 0 || input > 100) {
            throw ErrorResponseException("Allowed range is 0 - 100", 100, input)
        }

        return service.findSquare(input)
    }

    @GetMapping("square/{input}/reactive-throw")
    fun getSquare1(@PathVariable input: Int): Mono<ResponseEntity<Response>> {
        return Mono.just(input)
            .filter{ it > 0 }
            .flatMap { service.findSquare(input) }
            .map { ResponseEntity.ok().body(it) }
            .defaultIfEmpty(ResponseEntity.badRequest().build())
    }
}