package com.lv.fizzbuzzgame.ui.common.compose

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun OutlinedEditTextField(
    modifier: Modifier = Modifier,
    text: MutableState<String>,
    labelText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = modifier,
        value = text.value,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onValueChange = { value: String -> text.value = value },
        label = { Text(labelText) }
    )
}

