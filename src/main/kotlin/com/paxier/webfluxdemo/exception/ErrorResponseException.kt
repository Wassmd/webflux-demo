package com.paxier.webfluxdemo.exception

class ErrorResponseException(
    val msg: String,
    val errorCode: Int,
    val input: Int
): RuntimeException()