package com.lv.fizzbuzzgame.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SampleCitiesDataPreviewProvider : PreviewParameterProvider<List<String>> {
    override val values = sequenceOf(listOf("London", "Paris"))
}
