package com.lv.fizzbuzzgame.ui.feature.displayform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lv.fizzbuzzgame.ui.common.SingleFlowEvent
import com.lv.fizzbuzzgame.ui.common.dispatchers.CoroutineDispatchers
import com.lv.fizzbuzzgame.ui.domain.model.GameInputData
import com.lv.fizzbuzzgame.ui.domain.model.GameResult
import com.lv.fizzbuzzgame.ui.domain.usecase.ComputeGameUseCase
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.Error
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.GeneralError
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.InProgress
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.Init
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DisplayFormViewModel @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val computeGameUseCase: ComputeGameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SingleFlowEvent<DisplayFormUiState>>(SingleFlowEvent(Init))

    val uiState = _uiState as StateFlow<SingleFlowEvent<DisplayFormUiState>>

    fun computeGame(inputData: GameInputData) {
        viewModelScope.launch {
            postUiState(InProgress)
            val result = try {
                withContext(dispatchers.default) {
                    computeGameUseCase(inputData)
                }
            } catch (throwable: Throwable) {
                postUiState(GeneralError(throwable))
            }
            if (result is GameResult.Success) {
                postUiState(Success(result.data))
            } else if (result is GameResult.Error) {
                postUiState(Error(result.gameInputId))
            }
        }
    }

    private suspend fun postUiState(state: DisplayFormUiState) {
        _uiState.emit(SingleFlowEvent(state))
    }
}
