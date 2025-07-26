package com.elitec.compralibre.presentantion.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elitec.compralibre.R
import com.elitec.compralibre.presentantion.util.ButtonNavItems
import com.elitec.compralibre.presentantion.util.NavItemTypes

@Composable
fun ExtendedVerticalNavButton(
    navItemList: List<ButtonNavItems>,
    selectedPage: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(navItemList) { navButton ->
                val bottonColor = if (selectedPage==navButton.tittle) {
                    MaterialTheme.colorScheme.inverseSurface
                } else {
                    Color.Transparent
                }
                val animatedTextColor by animateColorAsState(
                    targetValue = bottonColor
                )
                Surface(
                    onClick = {
                        navButton.action(navButton.tittle)
                    },
                    modifier = Modifier
                        .width(110.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(15.dp),
                    color = animatedTextColor
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = navButton.icon,
                            contentDescription = null,
                        )
                        Text(
                            text = navButton.tittle
                        )
                    }
                }
            }
        }
        Image(
            painter = painterResource(R.drawable.pregoneros_clean),
            contentDescription = null,
            modifier = Modifier.width(110.dp)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ExtendedVerticalNavButtonPreview() {
    var selectedPage by remember { mutableStateOf("Principal") }
    val nav = listOf(
        ButtonNavItems(NavItemTypes.Principal.name, Icons.Default.Home, { selectedPage = NavItemTypes.Principal.name }),
        ButtonNavItems(NavItemTypes.Buscar.name, Icons.Default.Search, { selectedPage = NavItemTypes.Buscar.name }),
        ButtonNavItems(NavItemTypes.Crear.name, Icons.Filled.AddBox, { selectedPage = NavItemTypes.Crear.name }),
        ButtonNavItems(NavItemTypes.Chat.name, Icons.Default.Chat, { selectedPage = NavItemTypes.Chat.name }),
        ButtonNavItems(NavItemTypes.Perfil.name, Icons.Default.AccountCircle, { selectedPage = NavItemTypes.Perfil.name }),
    )
    ExtendedVerticalNavButton(
        navItemList = nav,
        selectedPage = selectedPage,
        modifier = Modifier.fillMaxHeight()
    )
}