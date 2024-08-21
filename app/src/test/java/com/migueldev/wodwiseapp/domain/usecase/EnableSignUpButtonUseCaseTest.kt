package com.migueldev.wodwiseapp.domain.usecase

import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class EnableSignUpButtonUseCaseTest {

    private val useCase = EnableSignUpButtonUseCase()

    @Test
    fun `GIVEN valid email, username, password and matching confirmPassword THEN return true`() {
        val email = "test@example.com"
        val password = "password"
        val confirmPassword = "password"

        val result = useCase(
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

        result shouldBe true
    }

    @Test
    fun `GIVEN invalid email WHEN invoke THEN return false`() {
        val email = "invalid-email"
        val password = "password"
        val confirmPassword = "password"

        val result = useCase(
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN short password WHEN invoke THEN return false`() {
        val email = "test@example.com"
        val password = "pass"
        val confirmPassword = "pass"

        val result = useCase(
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN non-matching passwords WHEN invoke THEN return false`() {
        val email = "test@example.com"
        val password = "password"
        val confirmPassword = "differentpassword"

        val result = useCase(
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN all invalid WHEN invoke THEN return false`() {
        val email = "invalid-email"
        val password = "pass"
        val confirmPassword = "differentpass"

        val result = useCase(
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

        result shouldBe false
    }
}
