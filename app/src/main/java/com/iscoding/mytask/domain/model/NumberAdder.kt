package com.iscoding.mytask.domain.model

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

//class NumberAdder(
//    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
//    private val delay: Int = 5000
//) {
//    suspend fun add(a: Int, b: Int): Int = withContext(dispatcher) {
//        delay(delay.toLong())
//        a + b
//    }
//}


//domain model-mappers will be added for clean arch demonstration (didn't add it as it is simple app with static api data)

private const val DELAY = 5000
class NumberAdder(
    private val dispatcher: CoroutineDispatcher =
        Dispatchers.IO,
    private val delay: Int = DELAY
) {
    suspend fun add(a: Int, b: Int): Flow<Int> {
        return flow {
            emit(a + b)
        }.onEach {
            delay(delay.toLong())
        }.flowOn(dispatcher)
    }
}