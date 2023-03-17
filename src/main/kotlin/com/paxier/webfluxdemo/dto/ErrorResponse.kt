package com.paxier.webfluxdemo.dto

import org.springframework.http.HttpStatus

data class ErrorResponse(
 val title: String,
 val status: Int,
 val validationErrors: Collection<ValidationError> = emptyList(),
 val code: String? = null
) {
 constructor(httpStatus: HttpStatus, vararg validationErrors: ValidationError) :
         this(httpStatus.reasonPhrase, httpStatus.value(), validationErrors.asList())

 constructor(title: String, status: Int, vararg validationErrors: ValidationError) :
         this(title, status, validationErrors.asList())
}

data class ValidationError(
 val name: String,
 val reason: String
)
