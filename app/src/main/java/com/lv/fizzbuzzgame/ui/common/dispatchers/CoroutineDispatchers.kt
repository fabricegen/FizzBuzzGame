package com.lv.fizzbuzzgame.ui.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
}
