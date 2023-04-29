package com.titicorp.titi.ui.screen.chats.newchat

import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.navigation.NavController
import com.sendbird.android.SendbirdChat
import com.sendbird.android.channel.OpenChannel
import com.sendbird.android.params.OpenChannelCreateParams
import com.sendbird.uikit.fragments.OpenChannelFragment
import com.titicorp.titi.MainActivity

@Composable
fun NewChat(
    navController: NavController
) {
    val containerId = remember { View.generateViewId() }

    var channelUrl: String? by remember {
        mutableStateOf(null)
    }


    LaunchedEffect(Unit) {
        SendbirdChat.connect("user001") { user, connectionError ->
            if (connectionError != null) return@connect

            OpenChannel.getChannel("user001_user002") { gotChannel, getException ->
                if (getException != null) {
                    OpenChannel.createChannel(
                        params = OpenChannelCreateParams().apply {
                            this.channelUrl = "user001_user002"
                        }
                    ) { createdChannel, creationError ->
                        if (creationError != null) return@createChannel
                        createdChannel?.let {
                            channelUrl = it.url
                        }
                    }
                }

                gotChannel?.let {
                    channelUrl = it.url
                }

            }
        }
    }

    AndroidView(
        factory = {
            FragmentContainerView(it).apply {
                id = containerId
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { view ->
            channelUrl?.let {
                val fragment = OpenChannelFragment.Builder(it)
                    .setUseHeader(false)
                    .build()
                val fragmentManager = (view.context as MainActivity).supportFragmentManager
                fragmentManager.commit {
                    replace(containerId, fragment, "NewChat")
                }
            }
        }
    )
}
