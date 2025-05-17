package com.ecopedia.ecopedia_android.presentation.home.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.compose.DrawableImage
import com.ecopedia.ecopedia_android.base.compose.LightGray30
import com.ecopedia.ecopedia_android.base.compose.LightGreen
import com.ecopedia.ecopedia_android.base.compose.Pretendard
import com.ecopedia.ecopedia_android.base.compose.UrlImage

@Composable
fun HomeScreen(
    onCameraClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        HomeLogo()
        Spacer(modifier = Modifier.height(13.dp))
        HomeTopBar(onCameraClick = onCameraClick)
        Spacer(modifier = Modifier.height(15.dp))
        PlantStatusCard(
            title = "잘하고 있어요!",
            subtitle = "묘목에 싹이 텄어요",
            progress = 0.5f,
            count = 3,
            maxCount = 10
        )
        Spacer(modifier = Modifier.height(26.dp))
        DonationCard()
        Spacer(modifier = Modifier.height(21.dp))
        RecentList(
            imageUris = listOf(
                "https://example.com/image.jpg",
                "https://example.com/image.jpg",
            )
        )
    }
}

@Composable
fun HomeLogo() {
    DrawableImage(
        resId = R.drawable.home_logo,
        width = 179.dp,
        height = 54.dp,
        contentDescription = "앱 아이콘"
    )
}

@Composable
fun HomeTopBar(onCameraClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("나의 식물", fontFamily = Pretendard, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        CameraButton(onCameraClick = onCameraClick)
    }
}

@Composable
fun CameraButton(
    onCameraClick: () -> Unit = {}
) {
    Button(
        onClick = { onCameraClick() },
        colors = ButtonDefaults.buttonColors(containerColor = LightGreen),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 11.dp, vertical = 7.dp)
    ) {
        DrawableImage(
            resId = R.drawable.camera,
            size = 26.dp,
            contentDescription = "카메라 버튼"
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            "사진 찍기",
            fontFamily = Pretendard,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun PlantStatusCard(
    title: String = "잘하고 있어요!",
    subtitle: String = "묘목에 싹이 텄어요",
    progress: Float = 0.5f, // 0.0 ~ 1.0
    count: Int = 3,
    maxCount: Int = 10
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGray30
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row {
                Column(modifier = Modifier.weight(1.0f)) {
                    Text(
                        text = title,
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xff191919)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xff191919)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = LightGreen,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        DrawableImage(
                            resId = R.drawable.leaf,
                            size = 20.dp,
                            contentDescription = "후원 아이콘"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$count",
                            fontFamily = Pretendard,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = LightGreen
                        )
                    }
                }
                DrawableImage(
                    resId = R.drawable.plant_status_illust,
                    width = 134.dp,
                    height = 126.dp,
                    contentDescription = "식물 일러스트"
                )
            }
            Row(
                modifier = Modifier.wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFF0F0F0))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(progress)
                            .background(LightGreen)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = "$count / $maxCount",
                    fontFamily = Pretendard,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.width(79.dp))
            }
        }
    }
}

@Composable
fun DonationCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            DrawableImage(
                resId = R.drawable.donation_bg,
                contentDescription = "후원 배경",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x66000000))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "후원하러 가기",
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "식물을 지급하는 만큼 나무를 심어드립니다",
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun RecentList(
    imageUris: List<String> = listOf<String>()
) {
    Text(
        "최근 등록한 생물",
        fontFamily = Pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        imageUris.take(3).forEach { uri ->
            UrlImage(
                modifier = Modifier.clip(RoundedCornerShape(6.dp)),
                url = uri,
                width = 104.dp,
                height = 136.dp,
                contentDescription = "최근 등록 생물"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeTopBar() {
    HomeTopBar()
}

@Preview(showBackground = true)
@Composable
fun PreviewCameraButton() {
    CameraButton()
}

@Preview(showBackground = true)
@Composable
fun PreviewPlantStatusCard() {
    PlantStatusCard()
}

@Preview(showBackground = true)
@Composable
fun PreviewDonationCard() {
    DonationCard()
}

@Preview(showBackground = true)
@Composable
fun PreviewRecentList() {
    RecentList()
}
