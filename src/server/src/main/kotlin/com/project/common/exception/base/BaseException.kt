package com.project.common.exception.base

open class BaseException : Exception {
    val errorCode: ErrorCode
    val args: MutableList<String> = mutableListOf()

    constructor(errorCode: ErrorCode) : this(errorCode, "")

    constructor(errorCode: ErrorCode, vararg args: String) : super(errorCode.format(*args)) {
        this.errorCode = errorCode
        if (args.isNotEmpty()) {
            this.args.addAll(listOf(*args))
        }
    }
}