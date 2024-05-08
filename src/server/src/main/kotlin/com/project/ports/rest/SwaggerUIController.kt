package com.project.ports.rest

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.uri.UriBuilder
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.Hidden
import java.net.URI

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
open class SwaggerUIController {
    private val swaggerURI: URI = UriBuilder.of("/swagger-ui").path("index.html").build()

    @Get
    @Hidden
    open fun home(): HttpResponse<Any> {
        return HttpResponse.seeOther(this.swaggerURI)
    }
}