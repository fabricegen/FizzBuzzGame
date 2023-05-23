package com.lv.fizzbuzzgame.ui.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class CoroutineDispatchersImpl @Inject constructor(
    @DefaultDispatcher override val default: CoroutineDispatcher,
    @MainDispatcher override val main: CoroutineDispatcher,
) : CoroutineDispatchers
