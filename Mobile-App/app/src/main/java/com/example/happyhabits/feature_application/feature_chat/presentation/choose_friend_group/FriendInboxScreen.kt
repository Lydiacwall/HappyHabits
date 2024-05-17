package com.example.happyhabits.feature_application.feature_chat.presentation.choose_friend_group
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.happyhabits.feature_application.presentation.util.Screen

@Composable
fun FriendInboxScreen(
    navController: NavController,
    viewModel: FriendInboxViewModel = hiltViewModel()
) {
    val state by viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.conversation) { friendGroup ->
                val friendName: String = friendGroup.friendUsername.trim()
                val groupId: String = friendGroup.groupId
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp)
                        .clickable { navController.navigate(Screen.ChatPageScreen.route + "?friendUsername=${friendName}" + "?groupId=${groupId}")}
                ) {
                    Text(
                        text = friendGroup.friendUsername,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}