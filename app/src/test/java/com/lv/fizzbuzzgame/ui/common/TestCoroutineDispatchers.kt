package com.lv.fizzbuzzgame.ui.common

import com.lv.fizzbuzzgame.ui.common.dispatchers.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

class TestCoroutineDispatchers(testDispatcher: TestDispatcher) : CoroutineDispatchers {
    override val default: CoroutineDispatcher = testDispatcher
    override val main: CoroutineDispatcher = testDispatcher
}
