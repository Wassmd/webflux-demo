package com.paxier.webfluxdemo.controller

import com.paxier.webfluxdemo.dto.MultiplyRequestDto
import com.paxier.webfluxdemo.dto.Response
import com.paxier.webfluxdemo.service.ReactiveMathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("reactive-math")
class ReactiveMathController(@Autowired val service: ReactiveMathService) {
    @GetMapping("/square/{number}")
    fun getSquare(@PathVariable number: Int): Mono<Response> {
        return service.findSquare(number)
    }

    @GetMapping("/table/{number}")
    fun getTable(@PathVariable number: Int): Flux<Response> {
        return service.multipleList(number)
    }

    @GetMapping("/table/{number}/stream", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    fun getTableStream(@PathVariable number: Int): Flux<Response> {
        return service.multipleList(number)
    }

    @PostMapping("/multiply")
    fun multiply(@RequestBody dto: Mono<MultiplyRequestDto>, @RequestHeader headers: Map<String, String>): Mono<Response> {
        println("Headers: $headers")
        return service.multiply(dto)
    }
}