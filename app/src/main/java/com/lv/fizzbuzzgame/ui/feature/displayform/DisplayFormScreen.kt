package com.lv.fizzbuzzgame.ui.feature.displayform

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lv.fizzbuzzgame.R
import com.lv.fizzbuzzgame.ui.common.SingleFlowEvent
import com.lv.fizzbuzzgame.ui.common.compose.OutlinedEditTextField
import com.lv.fizzbuzzgame.ui.common.compose.ProgressView
import com.lv.fizzbuzzgame.ui.common.compose.TopAppBarView
import com.lv.fizzbuzzgame.ui.domain.model.GameInputData
import com.lv.fizzbuzzgame.ui.domain.model.GameInputId
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.Error
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.GeneralError
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.InProgress
import com.lv.fizzbuzzgame.ui.feature.displayform.DisplayFormUiState.Success
import kotlinx.coroutines.launch

@Composable
fun DisplayFormRoute(
    onComplete: (String) -> Unit
) {
    val viewModel: DisplayFormViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    DisplayFormScreen(
        modifier = Modifier,
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onClickGoButton = { inputData ->
            viewModel.computeGame(inputData)
        },
        onComplete = {
            onComplete(it)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DisplayFormScreen(
    modifier: Modifier = Modifier,
    uiState: SingleFlowEvent<DisplayFormUiState>,
    snackBarHostState: SnackbarHostState,
    onClickGoButton: (GameInputData) -> Unit,
    onComplete: (String) -> Unit
) {
    val scope = rememberCoroutineScope()

    val snapshotState = uiState.get()?.apply {
        when (this) {
            is Success -> {
                onComplete(data)
            }

            is Error, is GeneralError -> {
                val errorMsg = if (this is Error) {
                    when (gameInputId) {
                        GameInputId.FIRST_STRING -> stringResource(id = R.string.first_string_msg)
                        GameInputId.SEC_STRING -> stringResource(id = R.string.sec_string_msg)
                        GameInputId.FIRST_INTEGER -> stringResource(id = R.string.first_integer_msg)
                        GameInputId.SEC_INTEGER -> stringResource(id = R.string.sec_integer_msg)
                        GameInputId.LIMIT -> stringResource(id = R.string.limit_string_msg)
                    }
                } else {
                    stringResource(id = R.string.general_error_msg)
                }

                LaunchedEffect(uiState) {
                    scope.launch {
                        snackBarHostState.showSnackbar(errorMsg)
                    }
                }
            }

            else -> {}
        }
    }

    val firstStringState = rememberSaveable { mutableStateOf("") }
    val secStringState = rememberSaveable { mutableStateOf("") }
    val firstIntegerState = rememberSaveable { mutableStateOf("") }
    val secIntegerState = rememberSaveable { mutableStateOf("") }
    val maxLimitState = rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBarView(
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                labelTextId = R.string.app_name
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
        if (snapshotState != null && snapshotState is InProgress) {
            ProgressView(text = stringResource(id = R.string.process_msg))
        } else {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    .padding(10.dp)
            ) {
                Card(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedEditTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = firstStringState,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        labelText = stringResource(id = R.string.first_string)
                    )
                    OutlinedEditTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = secStringState,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        labelText = stringResource(id = R.string.sec_string)
                    )
                    OutlinedEditTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = firstIntegerState,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        labelText = stringResource(id = R.string.first_integer)
                    )
                    OutlinedEditTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = secIntegerState,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        labelText = stringResource(id = R.string.sec_integer)
                    )
                    OutlinedEditTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = maxLimitState,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Go
                        ),
                        keyboardActions = KeyboardActions(
                            onGo = {
                                onClickGoButton(
                                    GameInputData(
                                        firstStringState.value,
                                        secStringState.value,
                                        firstIntegerState.value,
                                        secIntegerState.value,
                                        maxLimitState.value
                                    )
                                )
                            }
                        ),
                        labelText = stringResource(id = R.string.limit)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    modifier = modifier.align(Alignment.End),
                    onClick = {
                        onClickGoButton(
                            GameInputData(
                                firstStringState.value,
                                secStringState.value,
                                firstIntegerState.value,
                                secIntegerState.value,
                                maxLimitState.value
                            )
                        )
                    }) {
                    Text(
                        modifier = modifier.padding(start = 16.dp, end = 16.dp),
                        text = stringResource(id = R.string.cta_go)
                    )
                }
            }
        }
    }
}
