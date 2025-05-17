package com.ecopedia.ecopedia_android.data.datamodel

data class HomeDataResult(
    val donationStatus: DonationStatus,
    val plantStatus: PlantStatus,
    val recentCollected: List<RecentCollected>
)