package com.paxier.webfluxdemo.controller

import com.paxier.webfluxdemo.dto.Response
import com.paxier.webfluxdemo.service.MathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("math")
class MathController(@Autowired val service: MathService) {

    @GetMapping("/square/{number}")
    fun getSquare(@PathVariable number: Int): Response {
        return service.findSquare(number)
    }

    @GetMapping("/table/{number}")
    fun getTable(@PathVariable number: Int): List<Response> {
        return service.multipleList(number)
    }
}