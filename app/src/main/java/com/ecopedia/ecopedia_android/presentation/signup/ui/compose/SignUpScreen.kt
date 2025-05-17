package com.ecopedia.ecopedia_android.presentation.signup.ui.compose

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.compose.Pretendard
import com.ecopedia.ecopedia_android.presentation.MainActivity
import com.ecopedia.ecopedia_android.presentation.signin.ui.compose.CustomButton
import com.ecopedia.ecopedia_android.presentation.signin.ui.compose.UserProfileInputField
import com.ecopedia.ecopedia_android.presentation.signup.viewmodel.SignUpUiState

@Composable
fun SignUpScreen(
    onClickGoBackButton: () -> Unit,
    onSignUp: (String, String) -> Unit,
    signUpState: SignUpUiState
) {
    var nicknameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(signUpState) {
        if (signUpState is SignUpUiState.Success) {
            onClickGoBackButton()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
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

        if (isPasswordValid) {
            Spacer(Modifier.height(23.dp))
        } else {
            Text(
                "비밀번호는 8자 이상의 영어, 숫자로 이루어져야 합니다.",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xffFF5D77),
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.padding(horizontal = 47.dp, vertical = 5.dp)
            )
        }

        CustomButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                /*todo: 회원가입 버튼 누르고 기능 구현*/
                Log.d("signup", "$nicknameState, $passwordState")
                // 비밀번호 필드 검사 - 영어랑 숫자만으로 이루어져있냐?
                if (validatePassword(passwordState)) {
                    isPasswordValid = true

                    onSignUp(nicknameState, passwordState)
                } else {
                    isPasswordValid = false
                }
            },
            text = "회원가입"
        )
    }

    IconButton(
        onClick = {
            /*todo: 뒤로가기 (다시 로그인페이지로 이동) 구현*/
            Log.d("회원가입", "뒤로가기")
            onClickGoBackButton()
        },
        modifier = Modifier
            .wrapContentSize()
            .absoluteOffset(0.dp, 0.dp)
            .statusBarsPadding()
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_left),
            tint = Color.Black,
            contentDescription = null
        )
    }
}


fun validatePassword(
    passwordState: String
): Boolean {
    val regex = Regex("^[\\sa-zA-Z0-9]{8,20}$")
    if (regex.matches(passwordState)) {
        return true
    } else {
        return false
    }
}

@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen(onClickGoBackButton = {}, onSignUp = { _, _ -> }, signUpState = SignUpUiState.Idle)
}