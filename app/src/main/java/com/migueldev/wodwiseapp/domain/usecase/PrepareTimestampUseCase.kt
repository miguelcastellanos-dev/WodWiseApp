package com.migueldev.wodwiseapp.domain.usecase

import com.google.firebase.Timestamp
import javax.inject.Inject

class PrepareTimestampUseCase @Inject constructor() {
    operator fun invoke(date: Long?): Timestamp {
        return if (date != null) {
            val seconds = date / MILLISECONDS_IN_A_SECOND
            val nanoseconds = (
                (
                    date % MILLISECONDS_IN_A_SECOND
                    ) * NANOSECONDS_IN_A_MILLISECOND
                ).toInt()
            Timestamp(seconds, nanoseconds)
        } else {
            Timestamp.now()
        }
    }
}

const val MILLISECONDS_IN_A_SECOND = 1_000
const val NANOSECONDS_IN_A_MILLISECOND = 1_000_000
