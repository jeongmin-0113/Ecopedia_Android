package com.ecopedia.ecopedia_android.data.source.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/login") //로그인
    suspend fun login(@Body request: UserRequestDto)
    : BaseResponseDto<LoginResponseDto?>

    @POST("/signup") // 회원가입
    suspend fun signup(@Body request: UserRequestDto)
    : BaseResponseDto<SignUpResponseDto?>
}

data class LoginResponseDto(
    val token : String
)

data class BaseResponseDto<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: T?,
)

data class UserRequestDto(
    val nickname: String,
    val password: String
)

data class SignUpResponseDto(
    val message: String
)
