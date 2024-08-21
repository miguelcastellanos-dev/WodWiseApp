package com.migueldev.wodwiseapp.domain.usecase

import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EnableLoginButtonUseCaseTest {

    private lateinit var useCase: EnableLoginButtonUseCase

    @BeforeEach
    fun setUp() {
        useCase = EnableLoginButtonUseCase()
    }

    @Test
    fun `GIVEN valid email and password WHEN invoke THEN return true`() {
        val email = "test@example.com"
        val password = "password123"

        val result = useCase(
            email = email,
            password = password
        )

        result shouldBe true
    }

    @Test
    fun `GIVEN invalid email and valid password WHEN invoke THEN return false`() {
        val email = "invalid-email"
        val password = "password123"

        val result = useCase(
            email = email,
            password = password
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN valid email and short password WHEN invoke THEN return false`() {
        val email = "test@example.com"
        val password = "short"

        val result = useCase(
            email = email,
            password = password
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN invalid email and short password WHEN invoke THEN return false`() {
        val email = "invalid-email"
        val password = "short"

        val result = useCase(
            email = email,
            password = password
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN empty email and password WHEN invoke THEN return false`() {
        val email = ""
        val password = ""

        val result = useCase(
            email = email,
            password = password
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN email without domain and valid password WHEN invoke THEN return false`() {
        val email = "test@"
        val password = "password123"

        val result = useCase(
            email = email,
            password = password
        )

        result shouldBe false
    }

    @Test
    fun `GIVEN valid email and password with special characters WHEN invoke THEN return true`() {
        val email = "test@example.com"
        val password = "pa%w0rd$!"

        val result = useCase(
            email = email,
            password = password
        )

        result shouldBe true
    }
}
