package com.example.happyhabits.feature_application.feature_chat.presentation.friend_chat

import androidx.compose.material3.Text
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(
    viewModel: FriendChatViewModel = hiltViewModel()
) {

    val dynamicState = viewModel.state.value
    val staticState by viewModel.state

    var message by remember { mutableStateOf(staticState.messageContent) }

    // Function to send a message
    val sendMessage: () -> Unit = {
        // Call ViewModel function to send message
        viewModel.sendMessage(message)
        // Clear the message input field after sending
        message = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(dynamicState.conversation) { message ->
                var messageOwner: String = ""
                if (staticState.userId == message.senderId) {
                    messageOwner = staticState.username
                } else {
                    messageOwner = staticState.friendname
                }
                val isOwnMessage = message.senderId == staticState.userId
                Box(
                    contentAlignment = if (isOwnMessage) {
                        Alignment.CenterEnd
                    } else Alignment.CenterStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .background(
                                color = if (isOwnMessage) Color(0xFF64519A) else Color(0xFFD8DADE),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(8.dp)
                            .drawBehind {
//                                val cornerRadius = 10.dp.toPx()
//                                val triangleHeight = 20.dp.toPx()
//                                val triangleWidth = 25.dp.toPx()
                                val trianglePath = Path().apply {
                                    if (isOwnMessage) {
                                        moveTo(size.width - 10.dp.toPx(), size.height)// - cornerRadius)
                                        lineTo(size.width, size.height- 10.dp.toPx())// + triangleHeight)
                                        lineTo(size.width, size.height)
//                                        lineTo(
//                                            size.width - triangleWidth,
//                                            size.height - cornerRadius
//                                        )
                                        close()
                                    } else {
                                        moveTo(0f, size.height-10.dp.toPx()) //- cornerRadius)
                                        lineTo(10.dp.toPx(), size.height )//+ triangleHeight)
                                        lineTo(0f, size.height)

                                        //lineTo(triangleWidth, size.height - cornerRadius)
                                        close()
                                    }
                                }
                                drawPath(
                                    path = trianglePath,
                                    color = if (isOwnMessage)  Color(0xFF64519A) else Color(0xFFD8DADE)
                                )
                            }
//                            .background(
//                                color = if (isOwnMessage) Color.Green else Color.DarkGray,
//                                shape = RoundedCornerShape(10.dp)
//                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = messageOwner,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = message.content,
                            color = Color.White
                        )
                        Text(
                            text = message.timestamp,
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            TextField(
                value = message,
                onValueChange = { newValue -> message = newValue },
                placeholder = {
                    Text(text = "Enter a message")
                },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = sendMessage) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = Color(0xFF64519A)
                )
            }
        }
    }
}
