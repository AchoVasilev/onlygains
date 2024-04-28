package com.project.common.errormessages

import com.project.common.result.Error

class Message(private val code: String, private val description: String) {

    fun toError(): Error = Error(this.code, this.description)
}