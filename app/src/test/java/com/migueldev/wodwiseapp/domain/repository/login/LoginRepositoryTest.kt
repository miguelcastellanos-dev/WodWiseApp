package com.migueldev.wodwiseapp.domain.repository.login

import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.data.remote.datasource.login.LoginDatasource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginRepositoryTest {

    private val loginDatasource: LoginDatasource = mockk()

    private lateinit var repository: LoginRepository

    @BeforeEach
    fun setUp() {
        repository = LoginRepository(loginDatasource)
    }

    @Test
    fun `GIVEN successful login WHEN login THEN return FirebaseUser`(): Unit =
        runTest {
            val firebaseUser = mockk<FirebaseUser>()
            val email = "example@test.com"
            val password = "password1234"
            coEvery { loginDatasource.generateFirebaseUser(email, password) } returns
                Either.Right(firebaseUser)

            val result = repository.login(email, password)

            result.isRight() shouldBe true
            firebaseUser shouldBeEqualTo (result as Either.Right).value
            coVerifyOnce {
                loginDatasource.generateFirebaseUser(email, password)
            }
        }

    @Test
    fun `GIVEN login fails WHEN login THEN return Throwable`(): Unit =
        runTest {
            val exception = Exception("Test exception")
            val email = "example@test.com"
            val password = "wrong_password"
            coEvery { loginDatasource.generateFirebaseUser(email, password) } returns
                Either.Left(exception)

            val result = repository.login(email, password)

            result.isLeft() shouldBe true
            exception shouldBeEqualTo (result as Either.Left).value
            coVerifyOnce {
                loginDatasource.generateFirebaseUser(email, password)
            }
        }

    @Test
    fun `GIVEN successful password reset WHEN sendPasswordResetEmail THEN return Unit`() = runTest {
        val email = "example@test.com"
        coEvery { loginDatasource.sendPasswordResetEmail(email) } returns Either.Right(Unit)

        val result = repository.sendPasswordResetEmail(email)

        result.isRight() shouldBe true
        coVerifyOnce {
            loginDatasource.sendPasswordResetEmail(email)
        }
    }

    @Test
    fun `GIVEN successful delete user WHEN deleteUser THEN return Unit`() = runTest {
        coEvery { loginDatasource.deleteUser() } returns Either.Right(Unit)

        val result = repository.deleteUser()

        result.isRight() shouldBe true
        coVerifyOnce {
            loginDatasource.deleteUser()
        }
    }

    @Test
    fun `GIVEN failed delete user WHEN deleteUser THEN return Throwable`() = runTest {
        val exception = Exception("Test exception")
        coEvery { loginDatasource.deleteUser() } returns Either.Left(exception)

        val result = repository.deleteUser()

        result.isLeft() shouldBe true
        exception shouldBeEqualTo (result as Either.Left).value
        coVerifyOnce {
            loginDatasource.deleteUser()
        }
    }
}
