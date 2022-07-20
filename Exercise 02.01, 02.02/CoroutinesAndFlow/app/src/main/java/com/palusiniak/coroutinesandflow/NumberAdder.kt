package com.palusiniak.coroutinesandflow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

private const val DELAY = 5000

class NumberAdder(
    private val dispatcher: CoroutineDispatcher =
        Dispatchers.IO,
    private val delay: Int = DELAY
) {
    suspend fun add(a: Int, b: Int): Flow<Int> {
        return flow{
            emit(a+b)
        }.onEach {
            delay(delay.toLong())
        }.flowOn(dispatcher)


    }
}