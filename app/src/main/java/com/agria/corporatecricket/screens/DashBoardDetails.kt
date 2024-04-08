package com.agria.corporatecricket.screens

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agria.corporatecricket.Dtos.MatchesResponseDto
import com.agria.corporatecricket.Dtos.RegisterdTeams
import com.agria.corporatecricket.Dtos.TeamRequest
import com.agria.corporatecricket.Dtos.TournamentRegister
import com.agria.corporatecricket.Dtos.TournamentResponse
import com.agria.corporatecricket.Dtos.Winners
import com.agria.corporatecricket.Handlers.token

import com.agria.corporatecricket.R
import com.agria.corporatecricket.Router.PostRouter
import com.agria.corporatecricket.Router.Screen
import com.agria.corporatecricket.SessionManager.SessionManager
import com.agria.corporatecricket.ViewModel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardDetails() {

    val viewModel: MainViewModel = viewModel()
    var isSidebarOpen by remember { mutableStateOf(false) }

    var userId by remember { mutableIntStateOf(0) }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("session_data", Context.MODE_PRIVATE)
    userId = sharedPreferences.getInt("userId", 0)

    Surface(
        color = colorResource(id = R.color.homebg),
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {
            TopAppBar(
                title = {
                    Text(text = "Corporate Cricket", fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(start = 45.dp)) },
                navigationIcon = {
                    IconButton(onClick = { isSidebarOpen = !isSidebarOpen }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu",
                            tint = Color.Black
                        )
                    }
                }
            )

            if (isSidebarOpen) {
                Sidebar()
            }
            Spacer(modifier = Modifier.padding(70.dp))
            Button(
                onClick = {
                    PostRouter.navigateTo(Screen.TournamentScreen)
                },
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(55.dp),

                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
            ) {
                Text(text = "View Tournaments", color = Color.White, fontStyle = FontStyle.Italic)
            }
            Button(

                onClick = { PostRouter.navigateTo(Screen.TeamDetailsScreen)},
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(55.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700))
            ) {
                Text(text = " Team ", color = Color.White, fontStyle = FontStyle.Italic)
            }
            Button(

                onClick = { PostRouter.navigateTo(Screen.WinnerScreen) },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(55.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700))
            ) {
                Text(text = "Winners", color = Color.White, fontStyle = FontStyle.Italic)
            }
            Button(

                onClick = { PostRouter.navigateTo(Screen.ScheduledMatchesScreen)},
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(55.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700))
            ) {
                Text(text = " Matches", color = Color.White, fontStyle = FontStyle.Italic)
            }
            Text(
                text = "© Corporate Cricket 2024. All Rights Reserved.",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.LightGray ,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(start = 28.dp, top = 209.dp)

            )

        }
    }
}

@Preview
@Composable
fun DashBoardDetailsPreview() {
    DashBoardDetails()

//    WinnerItem(winner = Winners(1, "djidj", "sdds", "sdsd", 9293.0, "sdsad"))

}

@Composable
fun TournamentScreen() {
    val cricketViewModel: MainViewModel = viewModel()
    val viewstate by cricketViewModel.tournaments
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewstate.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            viewstate.error != null -> {
                Text("ERROR OCCURRED")
            }

            else -> {
                CricketScreen(tournaments = viewstate.list)
            }
        }
    }
    IconButton(
        onClick = { PostRouter.navigateTo(Screen.DashBoardDetails) },
        modifier = Modifier.padding(start = 2.dp)
    ) {
        Icon(
            Icons.Default.Menu, contentDescription = "Back",

            Modifier.size(100.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun CricketScreen(tournaments: List<TournamentResponse>) {
    LazyVerticalGrid(
        GridCells.Fixed(1), modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        items(tournaments) { category ->
            TournamnetData(response = category)
        }
    }
}


fun isUserRegisteredForTournament(
    user: Long,
    tournamentId: Long, tournaments: List<RegisterdTeams>
): Boolean {

    for (tournament in tournaments) {
        if ((tournament.tourId == tournamentId) && (tournament.userId == user)) {
            return true
        }
    }
    return false
}

@Composable
fun TournamnetData(response: TournamentResponse) {
    val viewModel: MainViewModel = viewModel()
    val registeredTeamsState by viewModel.registeredTeams.collectAsState()
    val teamNames = registeredTeamsState.map { it.admin }
    var userId by remember { mutableIntStateOf(0) }
    var token by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("session_data", Context.MODE_PRIVATE)
    userId = sharedPreferences.getInt("userId", 0)
    token = sharedPreferences.getString("token", "") ?: ""

    var register = TournamentRegister(userId.toLong(), response.tournamentId)
    var showDialog by remember { mutableStateOf(false) }
    val tournamentDate = SimpleDateFormat("yyyy-MM-dd").parse(response.startDate)
    val currentDate = Calendar.getInstance().time
    val isFutureDate = tournamentDate.after(currentDate)
    val isUserRegistered =
        isUserRegisteredForTournament(userId.toLong(), response.tournamentId, registeredTeamsState)
    val isJoining = remember { mutableStateOf(false) }


    if (isFutureDate) {
        Column(
            modifier = Modifier
                .padding(40.dp)
                .fillMaxSize()
                .width(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = colorResource(id = R.color.tournament)),

            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Text(
                text = "ID : ${response.tournamentId}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Name : ${response.tournamentName}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "prize : ${response.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Date : ${response.startDate}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )


            Text(
                text = "Time : ${response.startTime}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Row {
                if (isUserRegistered) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        enabled = false
                    ) {
                        Text(text = "Joined")
                    }
                } else {
                    Button(
                        onClick = {
                            isJoining.value = true

                            viewModel.registerTournament(register,token)
                        },
                        modifier = Modifier
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isUserRegistered || isJoining.value) Color.Red else Color.Green),
                        enabled = !(isUserRegistered || isJoining.value)
                    ) {
                        Text(text = if (isUserRegistered || isJoining.value) "Joined" else "Join")
                    }
                }

                Button(
                    onClick = {
                        viewModel.fetchAndProcess(response.tournamentId)
                        showDialog = true
                    },
                    modifier = Modifier
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text(text = "View")
                }
            }
            if (showDialog) {
                TeamListPopup(teamNames = teamNames) {
                    showDialog = false
                }
            }

        }

    }
}

@Composable
fun TeamListPopup(teamNames: List<String>, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        title = { Text(text = "Team List") },
        text = {
            Column {
                var count = 1

                teamNames.forEachIndexed { index, teamName ->
                    val displayCount = count + index
                    Text("$displayCount. $teamName", fontWeight = FontWeight.Bold)
                }
            }
        },
        confirmButton = {
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    )
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

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Card(
            modifier = Modifier
                .width(360.dp)
                .height(300.dp)
                .padding(start = 3.dp, top = 4.dp)
                .clip(RoundedCornerShape(30.dp)),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user), // Replace with your profile image resource
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.Black
                )

                Text(
                    text = "UserID: ${userId}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                Text(
                    text = "Username: ${username}",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )

                Text(
                    text = "Email: ${email}",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                Button(
                    onClick = {SessionManager.clearSession(context)
                        PostRouter.navigateTo(Screen.LoginScreen) },
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text(
                        text = "LogOut",
                        style = TextStyle(color = Color.Cyan)
                    )
                }
            }
        }
    }
}

@Composable
fun WinnerScreen() {
    val cricketViewModel: MainViewModel = viewModel()
    val winner = cricketViewModel.winner
    if (winner != null) {
        Box(modifier = Modifier.fillMaxSize()) {

            WinnersList(winner)
        }
        IconButton(
            onClick = { PostRouter.navigateTo(Screen.DashBoardDetails) },
            modifier = Modifier.padding(start = 2.dp)
        ) {
            Icon(
                Icons.Default.Menu, contentDescription = "Back",

                Modifier.size(100.dp),
                tint = Color.Black
            )
        }
    }


}

@Composable
fun WinnersList(winners: List<Winners>) {
    LazyVerticalGrid(
        GridCells.Fixed(1), modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        items(winners) { winner ->
            WinnerItem(winner = winner)
        }
    }
}

@Composable
fun WinnerItem(winner: Winners) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = Color.Blue)
        ) {
            append("Winner : ")
        }
        withStyle(
            style = SpanStyle(color = Color.Red)
        ) {
            append(winner.status)
        }
    }
    Column(
        modifier = Modifier
            .padding(40.dp)
            .fillMaxSize()
            .width(50.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.tournament)),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tournament ID : ${winner.tournamentId}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Tournament Name : ${winner.tournamentName}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Admin : ${winner.admin}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Team Name : ${winner.teamName}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Prize : $${winner.prize}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Text(
            text = annotatedString
            , style = MaterialTheme.typography.titleMedium,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }

}
//@Composable
//fun ScheduledMatchesScreen() {
//    val viewModel: MainViewModel = viewModel()
//    var scheduledMatches = viewModel.scheduledMatches.value
//    var tournamentId by remember { mutableStateOf("") }
//    var isPopupShown by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        IconButton(
//            onClick = { PostRouter.navigateTo(Screen.DashBoardDetails) },
//            modifier = Modifier.padding(start = 2.dp)
//        ) {
//            Icon(
//                Icons.Default.Menu, contentDescription = "Back",
//
//                Modifier.size(100.dp),
//                tint = Color.Black
//            )
//        }
//        OutlinedTextField(
//            value = tournamentId,
//            onValueChange = { tournamentId = it },
//            label = { Text("Enter Tournament ID") },
//            modifier = Modifier.fillMaxWidth(),
//            textStyle = TextStyle(fontSize = 16.sp),
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//                if (tournamentId.isNotEmpty()) {
//                    viewModel.scheduledMatches(tournamentId.toInt())
//                    isPopupShown = true
//                }
//            }, modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text("Submit")
//        }
//        if (isPopupShown) {
//            ScheduledMatchesPopup(
//                scheduledMatches = scheduledMatches,
//                onClose = { isPopupShown = false }
//            )
//        }
//    }
//}
@Composable
fun ScheduledMatchesScreen() {
    val viewModel: MainViewModel = viewModel()
    var scheduledMatches = viewModel.scheduledMatches.value
    var tournamentId by remember { mutableStateOf("") }
    var isPopupShown by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        IconButton(
            onClick = { PostRouter.navigateTo(Screen.DashBoardDetails) },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Back",

                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = tournamentId,
                onValueChange = { tournamentId = it },
                label = { Text("Enter Tournament ID") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (tournamentId.isNotEmpty()) {
                        viewModel.scheduledMatches(tournamentId.toInt())
                        isPopupShown = true
                    }
                }
            ) {
                Text("Submit")
            }
        }

        if (isPopupShown) {
            ScheduledMatchesPopup(
                scheduledMatches = scheduledMatches,
                onClose = { isPopupShown = false }
            )
        }
    }
}
@Composable
fun ScheduledMatchesPopup(scheduledMatches: List<MatchesResponseDto>, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Scheduled Matches") },
        text = {
            if (scheduledMatches.isNotEmpty()) {
                LazyColumn {
                    var matchId = 1
                    var lastTournamentName = ""

                    items(scheduledMatches) { match ->
                        if (match.tournamentName != lastTournamentName) {
                            MatchHeader(match.tournamentName)
                            lastTournamentName = match.tournamentName
                        }
                        MatchRow(matchId++, match)
                    }
                }
            } else {
                Text("No scheduled matches found")
            }
        },
        confirmButton = {
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    )
}


@Composable
fun MatchRow(matchId: Int,match: MatchesResponseDto) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Match ID: ${matchId}", fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Teams: ${match.teamName1} vs ${match.teamName2}", fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp),)
        Text(text = "Date: ${match.date}", fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Time: ${match.time}", fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.fillMaxWidth().size(2.dp))
    }
}
@Composable
fun MatchHeader(tournamentName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Tournament Name: $tournamentName" , color = Color.DarkGray, fontWeight = FontWeight.ExtraBold)
    }
}
















