package com.migueldev.wodwiseapp.presentation.screen.user.signup

import android.content.Context
import androidx.navigation.NavHostController
import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.domain.repository.login.SignUpRepository
import com.migueldev.wodwiseapp.domain.usecase.EnableSignUpButtonUseCase
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
class SignUpViewModelTest {

    private val enableSignupButtonUseCase: EnableSignUpButtonUseCase = mockk()
    private val signUpRepository: SignUpRepository = mockk()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val logger: Logger = mockk(relaxed = true)
    private val context: Context = mockk(relaxed = true)
    private val toastWrapper: ToastWrapper = mockk(relaxed = true)
    private val resourceProvider: ResourceProvider = mockk(relaxed = true)
    private val navController: NavHostController = mockk(relaxed = true)

    private lateinit var viewModel: SignUpViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        viewModel = SignUpViewModel(
            enableSignUpButtonUseCase = enableSignupButtonUseCase,
            signUpRepository = signUpRepository,
            ioDispatcher = testDispatcher,
            logger = logger,
            toastWrapper = toastWrapper,
            resourceProvider = resourceProvider
        )
    }

    @Test
    fun `GIVEN valid signup details WHEN onSignupChanged THEN state is updated correctly`(): Unit =
        runTest(testDispatcher) {
            val email = "test@example.com"
            val password = "password"
            val confirmPassword = "password"
            every {
                enableSignupButtonUseCase(email, password, confirmPassword)
            } returns true

            viewModel.onSignUpChanged(email, password, confirmPassword)

            val signUpState = viewModel.signUpState.first()
            signUpState.email shouldBeEqualTo email
            signUpState.password shouldBeEqualTo password
            signUpState.confirmPassword shouldBeEqualTo confirmPassword
            signUpState.isSignUpEnabled shouldBe true
        }

    @Test
    fun `GIVEN valid credentials WHEN registerWithEmailAndPassword THEN registration successful`(): Unit =
        runTest(testDispatcher) {
            val email = "test@example.com"
            val password = "password"
            val firebaseUser: FirebaseUser = mockk(relaxed = true)
            coEvery {
                signUpRepository.register(
                    email,
                    password
                )
            } returns Either.Right(firebaseUser)

            viewModel.registerWithEmailAndPassword(
                email,
                password,
                navController,
                context
            )

            coVerify { signUpRepository.register(email, password) }
            verify {
                logger.d(
                    "SignUpViewModel",
                    context.getString(
                        R.string.log_msg_successful_register,
                        firebaseUser.email
                    )
                )
            }
            verify { navController.navigate(Routes.LoginScreen.route) }
            verify { toastWrapper.show(R.string.toast_msg_registered_user) }
        }

    @Test
    fun `GIVEN invalid credentials WHEN registerWithEmailAndPassword THEN registration fails`(): Unit =
        runTest(testDispatcher) {
            val email = "test@example.com"
            val password = "password"
            val throwable = Throwable("Error")

            coEvery { signUpRepository.register(email, password) } returns Either.Left(throwable)

            viewModel.registerWithEmailAndPassword(
                email,
                password,
                navController,
                context
            )

            coVerify { signUpRepository.register(email, password) }
            verify {
                logger.e(
                    "SignUpViewModel",
                    context.getString(
                        R.string.log_msg_register_exception,
                        throwable.message ?: R.string.unknown_error
                    )
                )
            }
        }
}
