package com.lv.fizzbuzzgame.ui.feature.displayform

import com.lv.fizzbuzzgame.ui.common.CoroutineDispatcherRule
import com.lv.fizzbuzzgame.ui.common.TestCoroutineDispatchers
import com.lv.fizzbuzzgame.ui.domain.model.GameInputData
import com.lv.fizzbuzzgame.ui.domain.model.GameResult
import com.lv.fizzbuzzgame.ui.domain.usecase.ComputeGameUseCase
import io.mockk.coEvery
import io.mockk.coVerify
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
class DisplayFormViewModelUnitTest {

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    private val mockComputeGameUseCase = mockk<ComputeGameUseCase>()
    private val testDispatchers = TestCoroutineDispatchers(coroutineDispatcherRule.testDispatcher)

    private val sut = DisplayFormViewModel(testDispatchers, mockComputeGameUseCase)

    @Test
    fun `given input data when compute game then get result success`() = runTest {
        val mockResultData = ""
        val mockGameInputData = GameInputData("", "", "", "", "")
        coEvery { mockComputeGameUseCase.invoke(any()) }.returns(GameResult.Success(mockResultData))
        val collectJob = launch { sut.uiState.collect() }

        sut.computeGame(mockGameInputData)
        coVerify { mockComputeGameUseCase.invoke(any()) }
        val uiState = sut.uiState.value.get()
        assertTrue(uiState != null)
        assertTrue(uiState is DisplayFormUiState.Success)
        assertEquals(
            mockResultData,
            (uiState as DisplayFormUiState.Success).data
        )

        collectJob.cancel()
    }
}
