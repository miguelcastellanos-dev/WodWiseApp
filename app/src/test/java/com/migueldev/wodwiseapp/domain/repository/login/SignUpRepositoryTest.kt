package com.migueldev.wodwiseapp.domain.repository.login

import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.data.remote.datasource.signup.SignUpDatasource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SignUpRepositoryTest {

    private val signUpDatasource: SignUpDatasource = mockk()

    private lateinit var repository: SignUpRepository

    @BeforeEach
    fun setUp() {
        repository = SignUpRepository(signUpDatasource)
    }

    @Test
    fun `GIVEN successful login WHEN login THEN return FirebaseUser`(): Unit =
        runTest {
            val mockFirebaseUser = mockk<FirebaseUser>()
            val email = "example@test.com"
            val password = "password1234"
            coEvery { signUpDatasource.registerWithEmail(email, password) } returns
                Either.Right(mockFirebaseUser)

            val result = repository.register(email, password)

            result.isRight() shouldBe true
            mockFirebaseUser shouldBeEqualTo (result as Either.Right).value
            coVerifyOnce {
                signUpDatasource.registerWithEmail(email, password)
            }
        }

    @Test
    fun `GIVEN login fails WHEN login THEN return Throwable`(): Unit =
        runTest {
            val mockException = Exception("Test exception")
            val email = "example@test.com"
            val password = "wrong_password"
            coEvery { signUpDatasource.registerWithEmail(email, password) } returns
                Either.Left(mockException)

            val result = repository.register(email, password)

            result.isLeft() shouldBe true
            mockException shouldBeEqualTo (result as Either.Left).value
            coVerifyOnce {
                signUpDatasource.registerWithEmail(email, password)
            }
        }
}
