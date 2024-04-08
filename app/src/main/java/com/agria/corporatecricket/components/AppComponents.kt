package com.agria.corporatecricket.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agria.corporatecricket.Dtos.LogInRequest
import com.agria.corporatecricket.Dtos.SignUpRequest
import com.agria.corporatecricket.Handlers.ServiceBuilder
import com.agria.corporatecricket.Handlers.ServiceBuilder.logUser
import com.agria.corporatecricket.R
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen


@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.textColor),
        textAlign = TextAlign.Center
    )

}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.textColor),
        textAlign = TextAlign.Center
    )

}

@Composable
fun MyTextField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    var inputValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = inputValue,
        onValueChange = {
            inputValue = it
            onValueChanged(it)
        },
        label = { Text(value) },
        shape = RoundedCornerShape(14.dp)
    )
}


@Composable
fun MyTextFieldPassword(
    value: String,
    onValueChanged: (String) -> Unit
) {
    var inputValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = inputValue,
        onValueChange = {
            inputValue = it
            onValueChanged(it)
        },
        label = { Text(value) },
        shape = RoundedCornerShape(14.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}


@Composable
fun CheckBoxComponent(value: String, onTextSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value != checkedState.value })
        ClickableTextComponent(value = value, onTextSelected)

    }


}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val intial = "By continuing  you accept our "
    val privacy = "Privacy Policy"
    val and = " and "
    val terms = "Term of use"
    val annotatedStrig = buildAnnotatedString {
        append(intial)
        withStyle(style = SpanStyle(color = Color.Green)) {
            pushStringAnnotation(tag = privacy, annotation = privacy)
            append(privacy)
        }
        append(and)
        withStyle(style = SpanStyle(color = Color.Green)) {
            pushStringAnnotation(tag = terms, annotation = terms)
            append(terms)
        }

    }
    ClickableText(text = annotatedStrig, onClick = { offset ->
        annotatedStrig.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{$span}")
                if (span.item == terms || span.item == privacy) {
                    onTextSelected(span.item)
                }
            }
    })

}

@Composable
fun ButtonComponent(value: String, username: String, email: String, password: String) {
    var context = LocalContext.current
    Button(
        onClick = {
            if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                ServiceBuilder.createUser(SignUpRequest(username, email, password),context)


            } else {
                Toast.makeText(context, "fill the  all fileds", Toast.LENGTH_SHORT).show()

            }
        }, modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp), contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Cyan)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }


    }
}


@Composable
fun DividerText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.LightGray,
            thickness = 2.dp
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = "Or", fontSize = 14.sp, modifier = Modifier.padding(6.dp))
        Spacer(modifier = Modifier.padding(2.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.LightGray,
            thickness = 2.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(trying: Boolean = true, onTextSelected: (String) -> Unit) {
    val intial = if (trying) "Already Have account? " else "Don't have account?"
    val login = if (trying) "Login" else "SignUp"

    val annotatedStrig = buildAnnotatedString {
        append(intial)
        withStyle(style = SpanStyle(color = Color.Magenta)) {
            pushStringAnnotation(tag = login, annotation = login)
            append(login)
        }


    }
    ClickableText(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ), text = annotatedStrig, onClick = { offset ->
            annotatedStrig.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableLoginTextComponent", "{$span}")
                    if (span.item == login) {
                        onTextSelected(span.item)
                    }
                }
        })

}

@Composable
fun UnderLineNormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.LightGray,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline

    )

}

@Composable
fun LogButtonComponent(value: String, email: String, password: String, context: Context) {
    var context1 = LocalContext.current

    Button(
        onClick = {
            if (email.isNotBlank() && password.isNotBlank()) {
                logUser(LogInRequest(email, password), context)

            } else {
                Toast.makeText(context, "fill the details", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp), contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Cyan)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }


    }
}
