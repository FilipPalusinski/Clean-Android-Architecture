package com.palusiniak.coroutinesandflow

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class NumberAdderTest {
    @get:Rule
    val dispatcherTestRule = DispatcherTestRule()

    @ExperimentalCoroutinesApi
    @Test
    fun testAdd() = runBlockingTest {
        val adder = NumberAdder(dispatcher = dispatcherTestRule.testDispatcher,
            delay = 0)
        assertEquals(5, adder.add(1,4))
    }
}