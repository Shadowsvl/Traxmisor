package com.arch.data.domain

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidationUseCaseTest {

    private val emailValidationUseCase = EmailValidationUseCase()

    @Test
    fun `empty email returns false`() {
        val email = ""
        val isValid = emailValidationUseCase(email)
        assertFalse(isValid)
    }

    @Test
    fun `blank email returns false`() {
        val email = "   "
        val isValid = emailValidationUseCase(email)
        assertFalse(isValid)
    }

    @Test
    fun `valid email returns true`() {
        val email = "test@gmail.com"
        val isValid = emailValidationUseCase(email)
        assertTrue(isValid)
    }

    @Test
    fun `invalid email returns false`() {
        val email = "invalidemail"
        val isValid = emailValidationUseCase(email)
        assertFalse(isValid)
    }
}