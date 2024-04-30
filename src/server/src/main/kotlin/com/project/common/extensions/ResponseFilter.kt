package com.project.common.extensions

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.annotation.ResponseFilter
import io.micronaut.http.annotation.ServerFilter

@ServerFilter(Filter.MATCH_ALL_PATTERN)
class ResponseFilter {

    @ResponseFilter
    fun remapResponse(response: HttpResponse<*>): HttpResponse<*> {
        return response.fromResult()
    }
}