package com.lionel.mycomposedemo.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.lionel.mycomposedemo.ui.theme.JetchatTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetchatDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onProfileClicked: (String) -> Unit,
    onChatClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    JetchatTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                JetchatDrawerContent(
                    onProfileClicked = onProfileClicked,
                    onChatClicked = onChatClicked
                )
            }
        ) { content() }
    }

}