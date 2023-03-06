package com.finki.smartToBuyApp.api.response

import com.finki.smartToBuyApp.domain.User

sealed interface GetUserResponse

data class GetUserResponseSuccess(val user: User): GetUserResponse
data class GetUserResponseFailed(val error: String): GetUserResponse
