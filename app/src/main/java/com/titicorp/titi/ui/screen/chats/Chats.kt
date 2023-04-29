package com.titicorp.titi.ui.screen.chats

import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.navigation.NavHostController
import com.sendbird.uikit.activities.adapter.ChannelListAdapter
import com.sendbird.uikit.fragments.ChannelListFragment
import com.titicorp.titi.MainActivity
import com.titicorp.titi.R


@Composable
fun Chats(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavigation()
        Divider()
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Conversations()
        }
    }
}

@Composable
private fun TopNavigation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Chats",
            style = MaterialTheme.typography.h5,
        )
    }
}


@Composable
private fun Conversations() {
    AndroidView(
        factory = {
            val fragment = ChannelListFragment.Builder()
                .setUseHeader(false)
                .setChannelListAdapter(ChannelListAdapter())
                .setOnItemClickListener { view, position, data -> }
                .build()

            val containerId = View.generateViewId()
            val fragmentContainerView = FragmentContainerView(it).apply {
                id = containerId
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            val fragmentManager = (it as MainActivity).supportFragmentManager
            fragmentManager.commit {
                add(containerId, fragment, "Conversations")
            }
            fragmentContainerView
        }
    )
}