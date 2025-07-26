package com.elitec.compralibre.presentantion.navigation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elitec.compralibre.presentantion.components.BottomNavButton
import com.elitec.compralibre.presentantion.components.ExtendedBottomNavButton
import com.elitec.compralibre.presentantion.components.ExtendedVerticalNavButton
import com.elitec.compralibre.presentantion.components.VerticalNavButton
import com.elitec.compralibre.presentantion.util.ButtonNavItems
import com.elitec.compralibre.presentantion.util.DeviceConfiguration
import com.elitec.compralibre.presentantion.util.NavItemTypes

@Composable
fun HomeNavWrapper(
    userId: String,
    modifier: Modifier = Modifier
) {
    // UI States
    var selectedPage by remember { mutableStateOf("Principal") }

    //Manejo de tamaños de pantallas
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowsSizeClass(windowSizeClass)

    // Navigation Items
    val nav = listOf(
        ButtonNavItems(NavItemTypes.Principal.name, Icons.Default.Home, { page-> selectedPage = page }),
        ButtonNavItems(NavItemTypes.Buscar.name, Icons.Default.Search, { page-> selectedPage = page }),
        ButtonNavItems(NavItemTypes.Crear.name, Icons.Filled.AddBox, { page-> selectedPage = page }),
        ButtonNavItems(NavItemTypes.Chat.name, Icons.Default.Chat, { page-> selectedPage = page }),
        ButtonNavItems(NavItemTypes.Perfil.name, Icons.Default.AccountCircle, { page-> selectedPage = page }),
    )

    // Navegacion
    val navController = rememberNavController()

    val navHost = @Composable {
        NavHost(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            navController = navController,
            startDestination = Home(userId)
        ) {
            composable<Home> {

            }
            composable<Search> {

            }
            composable<Create> {

            }
            composable<Chat> {

            }
            composable<Account> {

            }
        }
    }

    when(deviceConfiguration) {
        DeviceConfiguration.SMART_CLOCK -> {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "SMART WATCH")
                }
            }
        }
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            Scaffold(
                containerColor = Color.Transparent,
                modifier = modifier.fillMaxSize(),
                bottomBar = {
                    Surface(
                        shadowElevation = 5.dp,
                        tonalElevation = 3.dp,
                        shape = RoundedCornerShape(
                            topEnd = 15.dp,
                            topStart = 15.dp
                        )
                    ) {
                        BottomNavButton(
                            navItemList = nav,
                            selectedPage = selectedPage,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding)
                ) {
                    navHost.invoke()
                }
            }
        }
        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Surface(
                        shadowElevation = 5.dp,
                        tonalElevation = 3.dp,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        VerticalNavButton(
                            navItemList = nav,
                            selectedPage = selectedPage,
                            verticalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                }
                Box(modifier = Modifier.weight(9f)) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(
                            top = 10.dp, end = 10.dp,
                        )
                    ) {
                        navHost.invoke()
                    }
                }
            }
        }
        DeviceConfiguration.AUTOMOBILE_DEVICE -> TODO()
        DeviceConfiguration.TABLET_PORTRAIT -> {
            Scaffold(
                containerColor = Color.Transparent,
                modifier = modifier.fillMaxSize(),
                bottomBar = {
                    Surface(
                        shadowElevation = 5.dp,
                        tonalElevation = 3.dp,
                        shape = RoundedCornerShape(
                            topEnd = 15.dp,
                            topStart = 15.dp
                        )
                    ) {
                        ExtendedBottomNavButton(
                            navItemList = nav,
                            selectedPage = selectedPage,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding)
                ) {
                    navHost.invoke()
                }
            }
        }
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                ) {
                    Surface(
                        shadowElevation = 5.dp,
                        tonalElevation = 3.dp,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        ExtendedVerticalNavButton(
                            navItemList = nav,
                            selectedPage = selectedPage,
                            modifier = Modifier.fillMaxHeight(),
                        )
                    }
                }
                Box(modifier = Modifier.weight(9f)) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(
                            top = 10.dp, end = 10.dp,
                        )
                    ) {
                        navHost.invoke()
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true, device = "spec:width=411dp,height=891dp"
)
@Composable
fun HomeNavWrapperPreview() {
    HomeNavWrapper(
        userId = 1.toString(),
        modifier = Modifier.fillMaxSize()
    )
}