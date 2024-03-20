package com.agria.corporatecricket.screens

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen
import com.agria.corporatecricket.components.ButtonComponent
import com.agria.corporatecricket.components.ClickableLoginTextComponent
import com.agria.corporatecricket.components.DividerText
import com.agria.corporatecricket.components.HeadingTextComponent
import com.agria.corporatecricket.components.MyTextField
import com.agria.corporatecricket.components.MyTextFieldPassword
import com.agria.corporatecricket.components.NormalTextComponent

@Composable
fun SignUpScreen(){

    var username by remember { mutableStateOf("") }
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
             NormalTextComponent("Hey hello")
             HeadingTextComponent(value = "Create an Account")
             Spacer(modifier = Modifier.height(16.dp))

             MyTextField(value = "username") { newValue ->
                 username = newValue // Update the value of username
             }
             Spacer(modifier = Modifier.height(10.dp))
             MyTextField(value = "email") { newValue ->
                 email = newValue // Update the value of username
             }

             Spacer(modifier = Modifier.height(10.dp))
//             MyTextFieldPassword( "enter password")
             MyTextFieldPassword(value = " password ") { newValue ->
                 password = newValue // Update the value in the caller's scope
             }
//             CheckBoxComponent(value = "data", onTextSelected = {
//                 PostRouter.navigateTo(Screen.TermsAndConditionsScreen)
//             })
             Spacer(modifier = Modifier.height(80.dp))
             ButtonComponent(value = "SignUp",username, email, password)


             Spacer(modifier = Modifier.height(10.dp))

             DividerText()
             ClickableLoginTextComponent(true, onTextSelected = {
                 PostRouter.navigateTo(Screen.LoginScreen)

             })
         }
    }
}
@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen()

}