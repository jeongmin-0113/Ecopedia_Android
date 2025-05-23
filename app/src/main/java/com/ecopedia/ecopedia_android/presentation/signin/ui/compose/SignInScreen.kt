package com.ecopedia.ecopedia_android.presentation.signin.ui.compose

import android.content.Intent
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.compose.Pretendard
import com.ecopedia.ecopedia_android.presentation.MainActivity
import com.ecopedia.ecopedia_android.presentation.signin.viewmodel.LoginUiState

@Composable
fun SignInScreen(
    onClickSignUpButton: () -> Unit,
    onLogin: (String, String) -> Unit,
    loginState: LoginUiState
) {
    var nicknameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(loginState) {
        if (loginState is LoginUiState.Success) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            if (context is android.app.Activity) {
                context.finish()
            }
        }
    }

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
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onLogin(nicknameState, passwordState)
            },
            text = "로그인"
        )

        when (loginState) {
            is LoginUiState.Loading -> Text("로그인 중...", color = Color.Gray)
            is LoginUiState.Error -> Text(loginState.message, color = Color.Red)
            else -> {}
        }

        TextButton(
            onClick = {
                onClickSignUpButton()
            }
        ) {
            Text(
                text = "회원가입",
                color = Color(0xffbebebe),
                style = TextStyle(
                    fontFamily = Pretendard
                )
            )
        }
    }
}

@Composable
fun UserProfileInputField(
    nicknameState: String,
    passwordState: String,
    onNicknameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            placeholderMessage = "닉네임",
            textState = nicknameState,
            onTextChange = onNicknameChange,
            isPassword = false,
            maxLength = 10
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomTextField(
            placeholderMessage = "비밀번호",
            textState = passwordState,
            onTextChange = onPasswordChange,
            isPassword = true,
            maxLength = 20
        )
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier
            .height(55.dp)
            .padding(horizontal = 42.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        colors = ButtonColors(
            contentColor = Color.White,
            containerColor = Color(0xff05D686),
            disabledContentColor = Color.White,
            disabledContainerColor = Color.Gray
        )
    ) {
        Text(text = text, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = Pretendard))
    }
}

// 커스텀 텍스트 필드
@Composable
fun CustomTextField(
    placeholderMessage: String,
    textState: String,
    onTextChange: (String) -> Unit,
    outlineColor: Color = Color(0xffF7F7F7),
    isPassword: Boolean = false,
    maxLength: Int,
) {
    BasicTextField(
        value = textState,
        onValueChange =  { if (it.length <= maxLength) onTextChange(it) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontFamily = Pretendard,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        ),
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        modifier = Modifier.wrapContentSize(),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 42.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xffF7F7F7), shape = RoundedCornerShape(size = 10.dp))
                    .border(1.dp, color = outlineColor, shape = RoundedCornerShape(size = 10.dp))
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (textState.isEmpty()) {
                    Text(
                        text = placeholderMessage,
                        fontSize = 12.sp,
                        color = Color(0xFF909ba9),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Pretendard,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                    )
                } else {
                    innerTextField()
                }
            }

        },
    )
}

@Preview
@Composable
fun UserProfileInputFieldPreview() {
    var nicknameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    UserProfileInputField(
        nicknameState = "",
        passwordState = "",
        onNicknameChange = { nicknameState = it },
        onPasswordChange = { passwordState = it }
    )
}

@Preview
@Composable
fun SignInPreview() {
    SignInScreen(
        onClickSignUpButton = {},
        onLogin = { _, _ -> },
        loginState = LoginUiState.Idle
    )
}