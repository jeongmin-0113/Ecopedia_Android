package com.ecopedia.ecopedia_android.presentation.home.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecopedia.ecopedia_android.base.compose.LightGreen
import com.ecopedia.ecopedia_android.base.compose.Pretendard

@Composable
fun DonationScreen(
    onClose: () -> Unit,
    onDonate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClose,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "닫기",
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "지금까지 4그루 후원하였습니다\n약 20,000원",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontFamily = Pretendard,
            modifier = Modifier
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.padding(end = 30.dp)
            ) {
                Text(
                    text = "나무: 3",
                    color = LightGreen,
                    fontSize = 15.sp,
                    fontFamily = Pretendard
                )
            }

            Button(
                onClick = onDonate,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightGreen
                )
            ) {
                Text(
                    text = "후원하기",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = Pretendard
                )
            }
        }
    }
} 