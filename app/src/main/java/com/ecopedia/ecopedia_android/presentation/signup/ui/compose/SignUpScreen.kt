package com.ecopedia.ecopedia_android.presentation.signup.ui.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.compose.Pretendard
import com.ecopedia.ecopedia_android.presentation.signin.ui.compose.CustomButton
import com.ecopedia.ecopedia_android.presentation.signin.ui.compose.UserProfileInputField

@Composable
fun SignUpScreen() {
    var nicknameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_signin_logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(80.dp))

        UserProfileInputField(
            nicknameState = nicknameState,
            passwordState = passwordState,
            onNicknameChange = { nicknameState = it },
            onPasswordChange = { passwordState = it }
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomButton(
            onClick = {
            /*todo: 회원가입 버튼 누르고 기능 구현*/
                Log.d("signup", "$nicknameState, $passwordState")
            },
            text = "회원가입"
        )
    }
}


@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen()
}