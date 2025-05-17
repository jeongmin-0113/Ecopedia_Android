package com.ecopedia.ecopedia_android.data.datamodel

import com.ecopedia.ecopedia_android.base.BaseResponse

data class DonationStatus(
    val availableDonationTreeCount: Int,
    val donatedTrees: Int,
    val donationWon: Int
): BaseResponse()