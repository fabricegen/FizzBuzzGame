package com.lv.fizzbuzzgame.ui.feature.displayresult

import androidx.lifecycle.SavedStateHandle
import com.lv.fizzbuzzgame.ui.common.CoroutineDispatcherRule
import com.lv.fizzbuzzgame.ui.common.TestCoroutineDispatchers
import com.lv.fizzbuzzgame.ui.feature.displayresult.DisplayResultUiState.Success
import com.lv.fizzbuzzgame.ui.feature.displayresult.navigation.dataArg
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DisplayResultViewModelUnitTest {

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    private val mockSavedStateHandler = mockk<SavedStateHandle>()
    private val testDispatchers = TestCoroutineDispatchers(coroutineDispatcherRule.testDispatcher)

    @Test
    fun `given input nav data when init then get result data`() = runTest {
        val mockResultData = "1,2,4,5"
        every { mockSavedStateHandler.get<String>(dataArg) }.returns(mockResultData)

        val sut = DisplayResultViewModel(mockSavedStateHandler, testDispatchers)
        val collectJob = launch { sut.uiState.collect() }
        assertTrue(sut.uiState.value is Success)
        assertEquals(mutableListOf("1", "2", "4", "5"), (sut.uiState.value as Success).data)

        collectJob.cancel()
    }
}
