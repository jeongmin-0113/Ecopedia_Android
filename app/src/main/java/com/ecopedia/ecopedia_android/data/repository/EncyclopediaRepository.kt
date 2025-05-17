package com.ecopedia.ecopedia_android.data.repository

import android.util.Log
import com.ecopedia.ecopedia_android.data.source.remote.EncyclopediaService
import com.ecopedia.ecopedia_android.utils.NetworkCallback
import com.ecopedia.ecopedia_android.utils.isErrorSuspend
import com.ecopedia.ecopedia_android.utils.isSuccessSuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EncyclopediaRepository @Inject constructor(
    private val service: EncyclopediaService
) {

    fun getAllItems() = flow {
        NetworkCallback(
            service.getAllItems()
        ).processResponse()
            .isSuccessSuspend {
                // 성공 로직
                Log.d("도감 데이터 가져오기", "가져오기 성공")
                emit(it.result)
            }.isErrorSuspend {
                // 오류 로직
                throw it
            }
    }.flowOn(Dispatchers.IO)
}