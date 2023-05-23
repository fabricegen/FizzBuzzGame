package com.lv.fizzbuzzgame.ui.feature.displayresult

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisplayResultScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun feed_init_display_screen() {
        val data = mutableListOf("1", "2", "3")
        composeTestRule.setContent {
            DisplayResultScreen(
                uiState = DisplayResultUiState.Success(data),
                modifier = Modifier
            )
        }

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(data[0])
            )
        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(data[1])
            )
        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(data[2])
            )
    }
}
