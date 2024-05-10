package com.project.infrastructure.utilities

import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
open class TransactionExecutor {
    @Transactional
    open fun <T> runInTransaction(transactionOperations: () -> T): T {
        return transactionOperations()
    }
}