package com.paxier.webfluxdemo.exception

import com.paxier.webfluxdemo.dto.ErrorResponse
import com.paxier.webfluxdemo.dto.ValidationError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handleException(ex: ErrorResponseException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.msg, HttpStatus.BAD_REQUEST.value(), ValidationError("Range", "should be within range"))

        return ResponseEntity.badRequest().body(errorResponse)
    }
}