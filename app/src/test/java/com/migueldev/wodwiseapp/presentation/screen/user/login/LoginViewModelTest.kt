package com.migueldev.wodwiseapp.presentation.screen.user.login

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.domain.repository.login.LoginRepository
import com.migueldev.wodwiseapp.domain.usecase.EnableLoginButtonUseCase
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private val enableLoginButtonUseCase: EnableLoginButtonUseCase = mockk()
    private val loginRepository: LoginRepository = mockk()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val logger: Logger = mockk(relaxed = true)
    private val toastWrapper: ToastWrapper = mockk(relaxed = true)
    private val context: Context = mockk(relaxed = true)
    private val userPreferences: UserPreferences = mockk(relaxed = true)
    private val navController: NavHostController = mockk(relaxed = true)
    private val resourceProvider: ResourceProvider = mockk(relaxed = true)

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = LoginViewModel(
            userPreferences = userPreferences,
            enableLoginButtonUseCase = enableLoginButtonUseCase,
            loginRepository = loginRepository,
            logger = logger,
            resourceProvider = resourceProvider,
            toastWrapper = toastWrapper,
            ioDispatcher = testDispatcher,
            mainDispatcher = testDispatcher
        )
    }

    @Test
    fun `GIVEN valid credentials WHEN onLoginChanged THEN state is updated correctly`(): Unit =
        runTest {
            val email = "example@test.com"
            val password = "password1234"
            every { enableLoginButtonUseCase(email, password) } returns true

            viewModel.onLoginChanged(email, password)

            val loginState = viewModel.loginState.first()
            loginState.email shouldBeEqualTo email
            loginState.password shouldBeEqualTo password
            loginState.isLoginEnabled shouldBe true
        }

    @Test
    fun `GIVEN valid credentials WHEN signInWithEmailAndPassword THEN login is successful`() =
        runTest(testDispatcher) {
            val email = "example@test.com"
            val password = "password1234"
            val firebaseUser: FirebaseUser = mockk(relaxed = true)
            coEvery { loginRepository.login(email, password) } returns Either.Right(firebaseUser)
            mockkStatic(Toast::class)
            every {
                Toast.makeText(
                    context,
                    "GO TO HOME SCREEN",
                    Toast.LENGTH_LONG
                )
            } returns mockk(relaxed = true)

            viewModel.signInWithEmailAndPassword(navController, email, password, context)

            coVerify {
                logger.d(
                    "LoginViewModel",
                    context.getString(
                        R.string.log_msg_successful_login,
                        firebaseUser.email
                    )
                )
            }
            coVerifyOnce { loginRepository.login(email, password) }
            unmockkStatic(Toast::class)
        }

    @Test
    fun `GIVEN invalid credentials WHEN signInWithEmailAndPassword THEN login fails`() =
        runTest(testDispatcher) {
            val email = "example@test.com"
            val password = "password1234"
            val throwable = Throwable("Error")
            coEvery { loginRepository.login(email, password) } returns Either.Left(throwable)

            viewModel.signInWithEmailAndPassword(navController, email, password, context)

            coVerifyOnce { loginRepository.login(email, password) }
            coVerifyOnce {
                logger.e(
                    "LoginViewModel",
                    context.getString(
                        R.string.log_msg_login_error,
                        throwable.message ?: R.string.unknown_error
                    )
                )
            }
        }
}
