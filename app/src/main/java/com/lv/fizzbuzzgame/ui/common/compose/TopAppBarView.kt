package com.lv.fizzbuzzgame.ui.common.compose

import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun TopAppBarView(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.primaryContainer,
    labelTextId: Int
) {
    TopAppBar(backgroundColor = backgroundColor,
        title = {
            Text(
                color = textColor,
                text = stringResource(id = labelTextId)
            )
        })
}
