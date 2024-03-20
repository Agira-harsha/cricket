package com.agria.corporatecricket.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.agria.corporatecricket.components.HeadingTextComponent

@Composable
fun TermsAndConditionsScreen() {
    Surface (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White))
    {
         HeadingTextComponent(value = "Terms of use")

    }

}
@Preview
@Composable
fun TermsAndConditionsScreenPreview(){
    TermsAndConditionsScreen()
}
