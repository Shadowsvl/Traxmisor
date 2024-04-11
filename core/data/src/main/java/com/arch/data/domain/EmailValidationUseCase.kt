package com.arch.data.domain

import javax.inject.Inject

class EmailValidationUseCase @Inject constructor() {

    companion object {
        private val EMAIL_PATTERN = Regex(
            "[a-zA-Z0-9\\+._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        )
    }

    operator fun invoke(email: String): Boolean {
        return when {
            email.isBlank() -> false
            else -> EMAIL_PATTERN.matches(email)
        }
    }
}