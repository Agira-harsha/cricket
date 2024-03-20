package com.agria.corporatecricket.screens

import android.telecom.Call.Details
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agria.corporatecricket.Handlers.ServiceBuilder
import com.agria.corporatecricket.components.ButtonComponent

@Composable

fun Details(){
    var numberText by remember { mutableStateOf("") }
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)
    ){
          Column(  modifier = Modifier
              .fillMaxSize()
              .padding(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center) {
              OutlinedTextField(
                  value = numberText,
                  onValueChange = { numberText = it },
                  label = { Text("Enter a number") },
                  singleLine = true,
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
              )
              Button(onClick = { ServiceBuilder.loadDetail(numberText.toInt()) }) {
                  Text(text = "Submit")
              }

          }
    }
}
@Preview
@Composable
fun DetailsPreview(){
    Details()
}


