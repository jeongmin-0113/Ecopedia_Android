package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

data class GridData(
    var idx: Int,
    var creatureName: String,
    var imageUrl: Int,
    var creatureExplain: String,
    var category: String,
    var location: String = ""
)

//
//{
//    "idx": 1,
//    "creatureName": "장수하늘소",
//    "creatureExplain": "희귀 곤충입니다.",
//    "category": "INSECTS",
//    "imageUrl": "https://ecopedia.s3.ap-northeast-2.amazonaws.com/abc.jpg"
//}