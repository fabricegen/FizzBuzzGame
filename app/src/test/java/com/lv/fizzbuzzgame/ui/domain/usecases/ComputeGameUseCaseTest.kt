package com.lv.fizzbuzzgame.ui.domain.usecases

import com.lv.fizzbuzzgame.ui.common.CoroutineDispatcherRule
import com.lv.fizzbuzzgame.ui.domain.model.GameInputData
import com.lv.fizzbuzzgame.ui.domain.model.GameInputId
import com.lv.fizzbuzzgame.ui.domain.model.GameResult
import com.lv.fizzbuzzgame.ui.domain.usecase.ComputeGameUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ComputeGameUseCaseTest {

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    private val mockGameInputData = mockk<GameInputData>()
    private val sut = ComputeGameUseCaseImpl()

    @Test
    fun `given input data when first string is empty then get error`() = runTest {
        every { mockGameInputData.firstString }.returns("")
        val result = sut.invoke(mockGameInputData)
        assertTrue(result is GameResult.Error)
        assertTrue((result as GameResult.Error).gameInputId == GameInputId.FIRST_STRING)
    }

    @Test
    fun `given input data when second string is empty then get error`() = runTest {
        every { mockGameInputData.firstString }.returns("firstString")
        every { mockGameInputData.secString }.returns("")
        val result = sut.invoke(mockGameInputData)
        assertTrue(result is GameResult.Error)
        assertTrue((result as GameResult.Error).gameInputId == GameInputId.SEC_STRING)
    }

    @Test
    fun `given input data when first integer is non integer then get error`() = runTest {
        every { mockGameInputData.firstString }.returns("firstString")
        every { mockGameInputData.secString }.returns("secString")
        every { mockGameInputData.firstInteger }.returns("")
        val result = sut.invoke(mockGameInputData)
        assertTrue(result is GameResult.Error)
        assertTrue((result as GameResult.Error).gameInputId == GameInputId.FIRST_INTEGER)
    }

    @Test
    fun `given input data when secInt integer is non integer then get error`() = runTest {
        every { mockGameInputData.firstString }.returns("firstString")
        every { mockGameInputData.secString }.returns("secString")
        every { mockGameInputData.firstInteger }.returns("3")
        every { mockGameInputData.secInteger }.returns("")
        val result = sut.invoke(mockGameInputData)
        assertTrue(result is GameResult.Error)
        assertTrue((result as GameResult.Error).gameInputId == GameInputId.SEC_INTEGER)
    }

    @Test
    fun `given input data when limit integer is non integer then get error`() = runTest {
        every { mockGameInputData.firstString }.returns("firstString")
        every { mockGameInputData.secString }.returns("secString")
        every { mockGameInputData.firstInteger }.returns("3")
        every { mockGameInputData.secInteger }.returns("5")

        every { mockGameInputData.limit }.returns("")
        val result = sut.invoke(mockGameInputData)
        assertTrue(result is GameResult.Error)
        assertTrue((result as GameResult.Error).gameInputId == GameInputId.LIMIT)
    }

    @Test
    fun `given input data when check input ok then get data result`() = runTest {
        val inputData = GameInputData("ta", "to", "3", "5", "20")
        val resultData = "1,2,ta,4,to,ta,7,8,ta,to,11,ta,13,14,tato,16,17,ta,19,to"
        val result = sut.invoke(inputData)

        assertTrue(result is GameResult.Success)
        assertTrue((result as GameResult.Success).data == resultData)
    }
}
