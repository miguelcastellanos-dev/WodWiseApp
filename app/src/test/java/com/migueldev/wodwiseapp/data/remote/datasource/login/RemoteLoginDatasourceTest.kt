package com.migueldev.wodwiseapp.data.remote.datasource.login

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

class RemoteLoginDatasourceTest {

    private val authService: AuthService = mockk()

    private lateinit var datasource: RemoteLoginDatasource

    @BeforeEach
    fun setUp() {
        datasource = RemoteLoginDatasource(authService)
    }

    @Test
    fun `GIVEN a success response WHEN generate user THEN get the expected user`() =
        runTest {
            val email = "example@test.com"
            val password = "password1234"
            val firebaseUser = mockk<FirebaseUser>()
            coEvery { authService.login(email, password) } returns firebaseUser

            val result = datasource.generateFirebaseUser(email, password)

            result shouldBeEqualTo firebaseUser.right()
            coVerifyOnce {
                authService.login(email, password)
            }
        }

    @Test
    fun `GIVEN a failure response WHEN generate Firebase user THEN get the error`() =
        runTest {
            val email = "example@test.com"
            val password = "password1234"
            coEvery { authService.login(email, password) } throws Throwable()

            val result = datasource.generateFirebaseUser(email, password)

            result.isLeft() shouldBe true
            coVerifyOnce {
                authService.login(email, password)
            }
        }
}
