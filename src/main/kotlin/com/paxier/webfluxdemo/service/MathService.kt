package com.paxier.webfluxdemo.service

import com.paxier.webfluxdemo.dto.Response
import com.paxier.webfluxdemo.utils.SleepUtils
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class MathService {

    fun findSquare(input: Int): Response {
       return Response(Date(), input * input)
    }

    fun multipleList(input: Int): List<Response> {
        val aList = ArrayList<Response>()
        for (i in 1..10) {
            SleepUtils.sleep(i)
            aList.add(Response(Date(), input * i))
        }

        return aList
    }
}