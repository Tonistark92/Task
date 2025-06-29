package com.iscoding.mytask

import com.iscoding.mytask.domain.repository.NumberAdder
import kotlinx.coroutines.test.*
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class NumberAdderTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    @Test
    fun testAdd() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val adder = NumberAdder(dispatcher = testDispatcher, delay = 0)
        val result = adder.add(2, 3)

        assertEquals(5, result)
    }
}