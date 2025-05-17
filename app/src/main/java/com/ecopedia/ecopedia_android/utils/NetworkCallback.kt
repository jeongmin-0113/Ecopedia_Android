package com.ecopedia.ecopedia_android.utils

import com.ecopedia.ecopedia_android.base.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class NetworkCallback<T : Any>(private var response: T?) {
    fun processResponse(): BaseCallback<T> {
        try {
            Timber.d("[HTTP_RESPONSE] $response")
            if (response != null) {
                if (response is BaseResponse) {
                    val res = response as BaseResponse
                    if (res.isSuccess == true) {
                        Timber.d("[HTTP_RESPONSE] code = ${res.code}, isSuccess = ${res.isSuccess}, message = ${res.message}")
                    } else {
                        return if (res.code?.isNotEmpty() == true) {
                            BaseCallback.Error(200, res.message ?: "")
                        } else {
                            BaseCallback.Error(
                                200, res.message ?: ""
                            )
                        }
                    }
                }
            } else {
                return BaseCallback.Error(500, "네트워크 에러")
            }
        } catch (e: Exception) {
            return BaseCallback.Error(500, "네트워크 에러")
        }

        return BaseCallback.Success(response as T)
    }
}

open class BaseCallback<out T : Any?> {
    data class Success<T : Any?>(val items: T) : BaseCallback<T>()
    data class Error(val errorCode: Int, var errorMessage: String) : BaseCallback<Nothing>() {
        fun toException(): Exception = Exception(errorMessage)
    }

    companion object {
        fun return500(methodName: String): Error {
            return Error(500, methodName)
        }

        fun convertError(err: Error): Error {
            return Error(err.errorCode, err.errorMessage)
        }
    }
}

fun <T> BaseCallback<T>.toFlow(): Flow<T> = flow {
    this@toFlow.isSuccessSuspend {
        emit(it)
    }.isErrorSuspend {
        throw it
    }
}


suspend fun <T> BaseCallback<T>.isSuccessSuspend(onAction: suspend (T) -> Unit = {}): BaseCallback<T> {
    val base = this
    if (base is BaseCallback.Success) {
        onAction(base.items)
    }
    return this
}

suspend fun <T> BaseCallback<T>.isErrorSuspend(onAction: suspend (Exception) -> Unit = {}): BaseCallback<T> {
    val base = this
    if (base is BaseCallback.Error) {
        onAction(base.toException())
    }
    return base
}
