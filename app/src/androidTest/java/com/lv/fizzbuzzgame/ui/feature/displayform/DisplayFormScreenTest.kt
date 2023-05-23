package com.lv.fizzbuzzgame.ui.feature.displayform

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lv.fizzbuzzgame.ui.common.SingleFlowEvent
import com.lv.fizzbuzzgame.ui.domain.model.GameInputId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisplayFormScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun feed_init_display_screen() {
        composeTestRule.setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            DisplayFormScreen(
                uiState = SingleFlowEvent(DisplayFormUiState.Init),
                modifier = Modifier,
                onClickGoButton = {},
                onComplete = {},
                snackBarHostState = snackBarHostState
            )
        }

        val firstString = context.resources.getString(com.lv.fizzbuzzgame.R.string.first_string)
        composeTestRule.onNodeWithText(firstString).assertExists()
        val secString = context.resources.getString(com.lv.fizzbuzzgame.R.string.sec_string)
        composeTestRule.onNodeWithText(secString).assertExists()
        val firstInteger = context.resources.getString(com.lv.fizzbuzzgame.R.string.first_integer)
        composeTestRule.onNodeWithText(firstInteger).assertExists()
        val secInteger = context.resources.getString(com.lv.fizzbuzzgame.R.string.sec_integer)
        composeTestRule.onNodeWithText(secInteger).assertExists()
        val limit = context.resources.getString(com.lv.fizzbuzzgame.R.string.limit)
        composeTestRule.onNodeWithText(limit).assertExists()
    }

    @Test
    fun feed_error_display_snackbar() {
        composeTestRule.setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            DisplayFormScreen(
                uiState = SingleFlowEvent(DisplayFormUiState.Error(GameInputId.FIRST_STRING)),
                modifier = Modifier,
                onClickGoButton = {},
                onComplete = {},
                snackBarHostState = snackBarHostState
            )
        }
        val firstString = context.resources.getString(com.lv.fizzbuzzgame.R.string.first_string_msg)
        composeTestRule.onNodeWithText(firstString).assertExists()
    }
}
