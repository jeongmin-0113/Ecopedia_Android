package com.ecopedia.ecopedia_android.data.repository

import com.ecopedia.ecopedia_android.data.source.remote.SampleService
import javax.inject.Inject

class SampleRepository @Inject constructor(private val service: SampleService) {
    suspend fun getData() = service.getData()
}