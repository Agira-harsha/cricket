package com.agria.corporatecricket.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen
import com.agria.corporatecricket.components.ButtonComponent
import com.agria.corporatecricket.components.ClickableLoginTextComponent
import com.agria.corporatecricket.components.DividerText
import com.agria.corporatecricket.components.HeadingTextComponent
import com.agria.corporatecricket.components.LogButtonComponent
import com.agria.corporatecricket.components.MyTextField
import com.agria.corporatecricket.components.MyTextFieldPassword
import com.agria.corporatecricket.components.NormalTextComponent
import com.agria.corporatecricket.components.UnderLineNormalTextComponent

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize())
        {

            Spacer(modifier = Modifier.height(80.dp))
            NormalTextComponent(value = "Login")
            HeadingTextComponent(value = "Welcome Back")
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(value = "email") { newValue ->
                email = newValue // Update the value of username
            }
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldPassword(value = "password") { newValue ->
                password = newValue // Update the value in the caller's scope
            }
            UnderLineNormalTextComponent(value = "forgot password")
            Spacer(modifier = Modifier.height(80.dp))
            val context = LocalContext.current
            LogButtonComponent(value = "Login", email, password, context)
            Spacer(modifier = Modifier.height(20.dp))
            DividerText()
            ClickableLoginTextComponent(false, onTextSelected = {
                PostRouter.navigateTo(Screen.SignUpScreen)
            })


        }


    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}