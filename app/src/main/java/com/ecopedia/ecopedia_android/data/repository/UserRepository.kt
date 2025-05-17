package com.ecopedia.ecopedia_android.data.repository

import com.ecopedia.ecopedia_android.data.source.local.TokenManager
import com.ecopedia.ecopedia_android.data.source.remote.UserRequestDto
import com.ecopedia.ecopedia_android.data.source.remote.UserService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: UserService, private val tokenManager: TokenManager) {

    fun login(nickname: String, password: String) = flow {
        try {
            val response = service.login(UserRequestDto(nickname, password))
            tokenManager.saveAccessToken(response.result!!.token)
        } catch (Exception e) {
            // todo
        }
    }
    fun signup(nickname: String, password: String) = service.signup(UserRequestDto(nickname, password))
}