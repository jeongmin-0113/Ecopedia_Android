package com.ecopedia.ecopedia_android.presentation.home.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.compose.DrawableImage
import com.ecopedia.ecopedia_android.base.compose.LightGreen
import com.ecopedia.ecopedia_android.base.compose.Pretendard

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