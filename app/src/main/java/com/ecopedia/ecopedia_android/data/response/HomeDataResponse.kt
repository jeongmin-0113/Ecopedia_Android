package com.ecopedia.ecopedia_android.data.response

import com.ecopedia.ecopedia_android.base.BaseResponse
import com.ecopedia.ecopedia_android.data.datamodel.HomeDataResult

data class HomeDataResponse(
    val result: HomeDataResult
) : BaseResponse()