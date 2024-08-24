package com.migueldev.wodwiseapp.domain.usecase

import arrow.core.Either
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import javax.inject.Inject

class HandleResultIAUseCase @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    operator fun invoke(
        result: Either<Throwable, String>,
        updateState: (String?) -> Unit,
    ) {
        result.fold(
            ifLeft = { throwable ->
                updateState(throwable.message)
            },
            ifRight = { response ->
                updateState(
                    response.takeIf { it.isNotEmpty() }
                        ?: resourceProvider.getString(
                            R.string.response_is_null_or_empty
                        )
                )
            }
        )
    }
}
