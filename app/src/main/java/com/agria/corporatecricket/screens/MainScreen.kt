package com.agria.corporatecricket.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agria.corporatecricket.Dtos.MatchesResponseDto
import com.agria.corporatecricket.R
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen
import com.agria.corporatecricket.ViewModel.MainViewModel

@Composable
fun MainScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)
    ) {
    }
    Column(modifier = Modifier.fillMaxSize()) {


        Row(modifier = Modifier.fillMaxSize()) {
            Sidebar()

        }
    }
}

@Composable
fun Sidebar() {
    var showProfile by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight()
            .background(Color.LightGray)
            .padding(3.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.user_removebg_preview),
            contentDescription = "profile",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally),
            tint = Color.Black
        )

        Button(
            onClick = { showProfile = !showProfile },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text("Profile")
        }
        if (showProfile) {
            ProfileSidebar(onClose = { showProfile = false })
        }
        Button(
            onClick = { },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text("Statistics")
        }

        Button(
            onClick = { },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp), // Rounded corners
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text("Settings")
        }
        Button(
            onClick = { PostRouter.navigateTo(Screen.HelpAndSupportScreen) },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp), // Rounded corners
            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
        ) {
            Text("Help & Support")
        }

    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
//    Sidebar()
    TeamDetailsScreen()
//    HelpAndSupportScreen()

}

@Composable
fun HelpAndSupportScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            // Back button
            IconButton(
                onClick = { PostRouter.navigateTo(Screen.DashBoardDetails) },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Back",

                    tint = Color.Black
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 25.dp, top = 100.dp)
            ) {
                Text(
                    text = "Help and Support",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "For any assistance or support, please contact us via:",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Email: agiracricket@gmail.com",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Phone: +919951674090",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Our support team is available to assist you during business hours.",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700))
                ) {
                    Text("Contact Us")
                }
                Text(
                    text = "© Corporate Cricket 2024. All Rights Reserved.",
                    modifier = Modifier.padding(top = 305.dp),
                    fontWeight = FontWeight.Bold

                )


            }
        }
    }
}

@Composable
fun TeamDetailsScreen() {
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("session_data", Context.MODE_PRIVATE)
     var userId = sharedPreferences.getInt("userId", 0)

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            IconButton(
                onClick = { PostRouter.navigateTo(Screen.DashBoardDetails) },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Back",

                    tint = Color.Black
                )
            }
            PlusButton(

                onClick = {viewModel.fetchTeam(userId)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )


        }

    }
}

@Composable
fun PlusButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.homebg))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Small floating action button.",
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}






