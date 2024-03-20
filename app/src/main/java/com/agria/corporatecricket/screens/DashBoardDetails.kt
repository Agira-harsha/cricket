package com.agria.corporatecricket.screens

import android.content.Context

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.agria.corporatecricket.Dtos.TournamentResponse

import com.agria.corporatecricket.R
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen
import com.agria.corporatecricket.ViewModel.MainViewModel


@Composable
fun DashBoardDetails() {
    var showProfile by remember { mutableStateOf(false) }

    Surface(
        color = colorResource(id = R.color.homebg),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
                    .clickable { showProfile = !showProfile }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user), // Replace with your profile icon resource
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(50.dp)
                )
            }
            if (showProfile) {
                ProfileSidebar(onClose = { showProfile = false })
            }
            Button(
                onClick = { PostRouter.navigateTo(Screen.TournamentScreen) },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "View Tournaments", color = Color.Black)
            }
            Button(

                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "     Create Team     ", color = Color.Black)
            }

        }
    }
}

@Preview
@Composable
fun DashBoardDetailsPreview() {
    DashBoardDetails()

    TournamentScreen()

}


@Composable
fun ProfileSidebar(onClose: () -> Unit) {
    var userId by remember { mutableIntStateOf(0) }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("session_data", Context.MODE_PRIVATE)
    userId = sharedPreferences.getInt("userId", 0)
    username = sharedPreferences.getString("userName", "") ?: ""
    email = sharedPreferences.getString("email", "") ?: ""
    Column(
        modifier = Modifier
            .width(350.dp)
            .height(300.dp)
            .padding(start = 85.dp, top = 10.dp)
            .background(Color.White)

    ) {
        Image(
            painter = painterResource(id = R.drawable.user), // Replace with your profile image resource
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Divider
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Text(
            text = "UserID: ${userId}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Text(
            text = "Username: ${username}",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Left,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Text(
            text = "Email: ${email}",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Left,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        Text(
            text = "Teamname: Agira",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Left,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

    }
}

@Composable
fun TournamentScreen() {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewstate.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            viewstate.error != null -> {
                Text("ERROR OCCURRED")
            }

            else -> {
                CategoryScreen(categories = viewstate.list)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<TournamentResponse>) {
    LazyVerticalGrid(GridCells.Fixed(1), modifier = Modifier.fillMaxSize()) {
        items(categories) { category ->
            CategoryItem(response = category)
        }
    }
}

// How each Items looks like
@Composable
fun CategoryItem(response: TournamentResponse) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize().background(color = colorResource(id = R.color.tournament)),

        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            text = "ID : ${response.tournamentId}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        Text(
            text = "Name : ${response.tournamentName}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        Text(
            text = "prize : ${response.price}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        Text(
            text = "Date : ${response.startDate}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )


        Text(
            text = "Time : ${response.startTime}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Join")
        }


    }
}









