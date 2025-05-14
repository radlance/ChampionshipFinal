package com.radlance.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.component.select.AppSelector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun inputField_showsHint_whenEmpty() {
        composeTestRule.setContent {
            EnterInputField(
                value = "",
                onValueChange = {},
                errorMessage = "",
                label = "Имя",
                hint = "Введите имя"
            )
        }

        composeTestRule.onNodeWithText("Введите имя").assertIsDisplayed()
    }

    @Test
    fun inputField_hasValue_hideHint() {
        composeTestRule.setContent {
            EnterInputField(
                value = "Иван",
                onValueChange = {},
                errorMessage = "",
                label = "Имя",
                hint = "Введите имя"
            )
        }

        composeTestRule.onNodeWithText("Иван").assertIsDisplayed()
        composeTestRule.onNodeWithText("Введите имя").assertIsNotDisplayed()
    }

    @Test
    fun inputField_showsError_whenErrorMessageSet() {
        composeTestRule.setContent {
            EnterInputField(
                value = "message",
                onValueChange = {},
                errorMessage = "Некорректное имя",
                label = "Имя",
                hint = "Введите имя"
            )
        }

        composeTestRule.onNodeWithText("Некорректное имя").assertExists()
    }

    @Test
    fun inputField_isDisabled_whenEnabledFalse() {
        composeTestRule.setContent {
            EnterInputField(
                value = "",
                onValueChange = {},
                errorMessage = "Некорректное имя",
                label = "Имя",
                hint = "Введите имя",
                enabled = false
            )
        }

        composeTestRule.onNodeWithTag("inputField").performClick()
        composeTestRule.onNodeWithTag("inputField").assertIsNotEnabled()
    }

    @Test
    fun selector_noValue_showHint() {
        composeTestRule.setContent {
            AppSelector(
                options = listOf("first, second, last"),
                selectedItem = null,
                onItemSelect = {},
                hint = "Пол",
                label = ""
            )
        }

        composeTestRule.onNodeWithText("Пол").assertIsDisplayed()
    }

    @Test
    fun selector_Click_showBottomSheet() {
        composeTestRule.setContent {
            AppSelector(
                options = listOf("first, second, last"),
                selectedItem = null,
                onItemSelect = {},
                hint = "Пол",
                label = ""
            )

        }
        composeTestRule.onNodeWithText("Пол").performClick()
        composeTestRule.onNodeWithTag("AppModalBottomSheet").assertIsDisplayed()
    }

    @Test
    fun selector_SelectedValue_showValue() {
        composeTestRule.setContent {
            AppSelector(
                options = listOf("first, second, last"),
                selectedItem = "first",
                onItemSelect = {},
                hint = "Пол",
                label = ""
            )

        }
        composeTestRule.onNodeWithText("first").assertIsDisplayed()
    }
}