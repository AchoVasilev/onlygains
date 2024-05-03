package com.project.infrastructure.security

interface HashService {
    fun hashText(text: String, salt: String): String

    fun matchHash(text: String, hash: String): Boolean
}