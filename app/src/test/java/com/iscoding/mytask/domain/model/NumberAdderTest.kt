package com.iscoding.mytask.domain.model

import com.iscoding.mytask.testutil.DispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NumberAdderTest {

    @get:Rule
    val dispatcherTestRule = DispatcherTestRule()

    @Test
    fun testAdd() = runTest {
        val adder = NumberAdder(
            dispatcher = dispatcherTestRule.testDispatcher,
            delay = 0 // skip delay for test
        )

        val result = adder.add(1, 4).first()

        assertEquals(5, result)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class NumberAdderTest2 {

    private val testDispatcher = StandardTestDispatcher()

    @Test
    fun `test add flow returns correct sum after delay`() = runTest {
        val adder = NumberAdder(dispatcher = testDispatcher, delay = 0) // delay = 0 for test

        val result = adder.add(2, 3).first()

        assertEquals(5, result)
    }

}