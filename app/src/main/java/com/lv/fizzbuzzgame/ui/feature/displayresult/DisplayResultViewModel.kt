package com.lv.fizzbuzzgame.ui.feature.displayresult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lv.fizzbuzzgame.ui.common.dispatchers.CoroutineDispatchers
import com.lv.fizzbuzzgame.ui.feature.displayresult.DisplayResultUiState.Init
import com.lv.fizzbuzzgame.ui.feature.displayresult.DisplayResultUiState.Success
import com.lv.fizzbuzzgame.ui.feature.displayresult.navigation.DisplayResultArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DisplayResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dispatchers: CoroutineDispatchers
) : ViewModel() {

    private val args = DisplayResultArgs(savedStateHandle)

    private val _uiState = MutableStateFlow<DisplayResultUiState>(Init)

    val uiState = _uiState as StateFlow<DisplayResultUiState>

    init {
        viewModelScope.launch() {
            val result = withContext(dispatchers.default) {
                args.data.split(",").toList()
            }
            _uiState.value = Success(result)
        }
    }
}
