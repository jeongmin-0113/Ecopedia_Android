package com.ecopedia.ecopedia_android.presentation.signin.ui.compose

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecopedia.ecopedia_android.R
import timber.log.Timber

@Composable
fun SignInScreen() {
    Log.d("TEST", "컴포즈 들어옴")
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
            nicknameState = "",
            passwordState = "",
            onNicknameChange = { nicknameState = it },
            onPasswordChange = { passwordState = it }
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomButton(
            onClick = {/*todo: 로그인 버튼 누르고 기능 구현*/},
            text = "로그인"
        )

        TextButton(
            onClick = { /*todo: 회원가입 스크린으로 이동*/ }
        ) {
            Text(
                text = "회원가입",
                color = Color(0xffbebebe)
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
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            placeholderMessage = "닉네임",
            textState = nicknameState,
            onTextChange = onNicknameChange
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomTextField(
            placeholderMessage = "비밀번호",
            textState = passwordState,
            onTextChange = onPasswordChange
        )
    }
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = Modifier
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
        Text(text= text, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
    }
}

// 커스텀 텍스트 필드
@Composable
fun CustomTextField(
    placeholderMessage: String,
    textState: String,
    onTextChange: (String) -> Unit,
    outlineColor: Color = Color(0xffF7F7F7)
) {
    BasicTextField(
        value = if (textState.isEmpty()) placeholderMessage else textState,
        onValueChange = onTextChange,
        singleLine = true,
        textStyle = if (textState.isEmpty()) {
            TextStyle(
                fontSize = 16.sp,
                color = Color(0xff999999)
            )
        } else {
            TextStyle(
                fontSize = 16.sp,
                color = Color(0xffffffff)
            )
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
                innerTextField()
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
    SignInScreen()
}