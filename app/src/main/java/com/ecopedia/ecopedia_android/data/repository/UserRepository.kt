package com.ecopedia.ecopedia_android.data.repository

import android.util.Log
import com.ecopedia.ecopedia_android.data.source.local.TokenManager
import com.ecopedia.ecopedia_android.data.source.remote.UserRequestDto
import com.ecopedia.ecopedia_android.data.source.remote.UserService
import com.ecopedia.ecopedia_android.utils.NetworkCallback
import com.ecopedia.ecopedia_android.utils.isErrorSuspend
import com.ecopedia.ecopedia_android.utils.isSuccessSuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService,
    private val tokenManager: TokenManager
) {

    fun login(nickname: String, password: String) = flow {
        NetworkCallback(
            service.login(request = UserRequestDto(nickname = nickname, password = password))
        ).processResponse()
            .isSuccessSuspend {
                Log.d("TEST", "result: ${it.result.token}")
                tokenManager.saveAccessToken(it.result.token)
                emit(Unit)
            }.isErrorSuspend {
                throw it
            }
    }.flowOn(Dispatchers.IO)

    fun signup(nickname: String, password: String) = flow {
        NetworkCallback(
            service.signup(UserRequestDto(nickname, password))
        ).processResponse()
            .isSuccessSuspend {
                emit(Unit)
            }.isErrorSuspend {
                throw it
            }
    }.flowOn(Dispatchers.IO)

}