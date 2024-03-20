    package com.agria.corporatecricket.screens

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.material3.Surface
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.rememberCoroutineScope
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import com.agria.corporatecricket.R
    import com.agria.corporatecricket.Router.PostRouter
    import com.agria.corporatecricket.Router.Screen
    import kotlinx.coroutines.delay
    import kotlinx.coroutines.launch

    @Composable
    fun DashBoard(){
        var timerStarted by remember { mutableStateOf(false) }

        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(true) {
            if (!timerStarted) {
                coroutineScope.launch {
                    delay(5000L)
                   PostRouter.navigateTo(Screen.DashBoardDetails)
                }
                timerStarted = true
            }
        }
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
        ){

            Image(
                painter = painterResource(id = R.drawable.home), // Replace R.drawable.designer_1_ with your image resource
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )


        }
    }
    @Preview
    @Composable
    fun DashBoardPreview(){
        DashBoard()
    }