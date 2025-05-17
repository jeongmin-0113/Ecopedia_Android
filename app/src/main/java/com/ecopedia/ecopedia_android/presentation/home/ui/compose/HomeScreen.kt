package com.ecopedia.ecopedia_android.presentation.home.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        Spacer(modifier = Modifier.height(16.dp))
        // 상단 로고
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFF4FC08D), shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Ecopedia",
                color = Color(0xFF4FC08D),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // "나의 식물" + 사진찍기 버튼
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("나의 식물", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Button(
                onClick = onCameraClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4FC08D)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "사진 찍기",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("사진 찍기", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "잘하고 있어요!" 카드
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0xFFB2DFDB), shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("잘하고 있어요!", fontWeight = FontWeight.Bold)
                        Text("식물에 싹이 돋았어요")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFF4FC08D), shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("50", color = Color(0xFF4FC08D), fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "50 / 100",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 후원하러 가기 카드
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xAA4FC08D))
            ) {
                Text(
                    "후원하러 가기",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 최근 등록한 도감
        Text("최근 등록한 도감", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(3) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .size(90.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFB2DFDB))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}