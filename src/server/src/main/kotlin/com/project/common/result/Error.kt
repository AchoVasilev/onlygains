package com.project.common.result

class Error(val code: String, val description: String) {

    companion object {
        @JvmStatic
        fun none(): Error = Error("", "")

        @JvmStatic
        fun nullValue(): Error = Error("errors.nullValue", "Null value was provided")
    }
}