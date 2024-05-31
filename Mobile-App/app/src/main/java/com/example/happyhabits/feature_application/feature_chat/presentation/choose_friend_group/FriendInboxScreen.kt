package com.example.happyhabits.feature_application.feature_chat.presentation.choose_friend_group
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.example.happyhabits.R

@Composable
fun FriendInboxScreen(
    navController: NavController,
    viewModel: FriendInboxViewModel = hiltViewModel()
) {
    val state by viewModel.state
    val colors = listOf(Color(0xffD8DADE),Color(0xff7E70A9),Color(0xff64519A))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(Color(0xffF3F3F3)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.inbox_icon),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(35.dp)
            )

            Text(
                text = "Inbox",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp)
            )


        }
        Box(
            modifier= Modifier
                .background(brush = Brush.verticalGradient(colors = colors))
                .fillMaxSize()
                .padding(top=20.dp)
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
                        .padding(horizontal = 5.dp)
                        .height(100.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .clickable { navController.navigate(Screen.ChatPageScreen.route + "?friendUsername=${friendName}" + "?groupId=${groupId}") },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter= painterResource(id = R.drawable.anonymous_user_purple),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)

                        )

                        Spacer(modifier=Modifier.width(20.dp))


                        Text(
                            text = friendGroup.friendUsername,
                            //style = MaterialTheme.typography.bodyMedium,
                            fontSize=20.sp,
                            fontWeight= FontWeight.Bold
                        )

                        Spacer(modifier=Modifier.width(20.dp))

                        Image(
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(40.dp)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        }
    }
}