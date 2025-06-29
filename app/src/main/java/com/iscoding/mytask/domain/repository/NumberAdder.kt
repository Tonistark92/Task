package com.iscoding.mytask.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class NumberAdder(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val delay: Int = 5000
) {
    suspend fun add(a: Int, b: Int): Int = withContext(dispatcher) {
        delay(delay.toLong())
        a + b
    }
}