package com.migueldev.wodwiseapp.data.remote.datasource.signup

import arrow.core.right
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.data.remote.service.AuthService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RemoteSignUpDatasourceTest {

    private val authService: AuthService = mockk()
    private val mockFirebaseUser = mockk<FirebaseUser>()

    private lateinit var datasource: RemoteSignUpDatasource

    @BeforeEach
    fun setUp() {
        datasource = RemoteSignUpDatasource(authService)
    }

    @Test
    fun `GIVEN a success response WHEN register with email THEN get the expected user`() =
        runTest {
            val email = "example@test.com"
            val password = "password1234"
            coEvery { authService.register(email, password) } returns mockFirebaseUser

            val result = datasource.registerWithEmail(email, password)

            result shouldBeEqualTo mockFirebaseUser.right()
            coVerifyOnce {
                authService.register(email, password)
            }
        }

    @Test
    fun `GIVEN a failure response WHEN register with email THEN get the error`() =
        runTest {
            val email = "example@test.com"
            val password = "password1234"
            coEvery { authService.register(email, password) } throws Throwable()

            val result = datasource.registerWithEmail(email, password)

            result.isLeft() shouldBe true
            coVerifyOnce {
                authService.register(email, password)
            }
        }
}
