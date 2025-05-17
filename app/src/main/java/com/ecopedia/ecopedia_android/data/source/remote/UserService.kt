package com.ecopedia.ecopedia_android.data.source.remote

import com.ecopedia.ecopedia_android.base.BaseResponse
import com.ecopedia.ecopedia_android.data.datamodel.DonationStatus
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/login") //로그인
    suspend fun login(@Body request: UserRequestDto)
            : LoginResponseDto

    @POST("/signup") // 회원가입
    suspend fun signup(@Body request: UserRequestDto)
            : SignUpResponseDto

    @POST("/donation/trees")
    suspend fun donation(): DonationStatus
}


data class LoginResponseDto(
    val result: Token
) : BaseResponse()

data class Token(
    val token: String
)

data class UserRequestDto(
    val nickname: String,
    val password: String
) : BaseResponse()

data class SignUpResponseDto(
    val result: String
) : BaseResponse()
