package com.radlance.championshipfinal.presentation.auth.common

import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.common.ProvideResources
import javax.inject.Inject

interface ValidationAuth {

    fun validFirstName(value: String): String

    fun validLastName(value: String): String

    fun validPatronymic(value: String): String

    fun validDateOfBirth(value: String): String

    fun validTelegram(value: String): String

    fun validEmail(value: String): String

    fun validPassword(value: String): String

    fun validConfirmPassword(password: String, confirm: String): String

    class Base @Inject constructor(
        private val provideResources: ProvideResources
    ) : ValidationAuth {
        override fun validFirstName(value: String): String {
            return if (value.isBlank()) {
                provideResources.string(R.string.enter_your_first_name)
            } else ""
        }

        override fun validLastName(value: String): String {
            return if (value.isBlank()) {
                provideResources.string(R.string.enter_your_last_name)
            } else ""
        }

        override fun validPatronymic(value: String): String {
            return if (value.isBlank()) {
                provideResources.string(R.string.enter_your_patronymic)
            } else ""
        }

        override fun validDateOfBirth(value: String): String {
            return if (value.isBlank()) {
                provideResources.string(R.string.enter_your_date_of_birth)
            } else ""
        }

        override fun validTelegram(value: String): String {
            return if (value.isBlank()) {
                provideResources.string(R.string.enter_your_telegram)
            } else ""
        }

        override fun validEmail(value: String): String {
            return when {
                value.isBlank() -> provideResources.string(R.string.enter_your_email)
                !Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$").matches(value) -> provideResources.string(R.string.invalid_email_format)
                else -> ""
            }
        }

        override fun validPassword(value: String): String {
            return when {
                value.isBlank() -> provideResources.string(R.string.enter_your_password)
                !Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct}).{8,}$").matches(value) -> provideResources.string(R.string.invalid_password_format)
                else -> ""
            }
        }

        override fun validConfirmPassword(password: String, confirm: String): String {
            return when {
                confirm.isBlank() -> provideResources.string(R.string.reply_password)
                confirm != password -> provideResources.string(R.string.passsword_are_not_the_same)
                else -> ""
            }
        }
    }
}