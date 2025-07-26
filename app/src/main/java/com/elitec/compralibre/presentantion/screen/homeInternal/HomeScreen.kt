package com.elitec.compralibre.presentantion.screen.homeInternal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elitec.compralibre.presentantion.util.DeviceConfiguration

@Composable
fun HomeScreen(
    deviceConfiguration: DeviceConfiguration,
    modifier: Modifier = Modifier
) {
    // Ui States
    var nameToFind by rememberSaveable { mutableStateOf("") }
    var categorySelected by remember { mutableStateOf("Alimentos") }

    /*// Caterories
    val categories = listOf(
        CategoriesItem(UUID.randomUUID().toString(),"Alimentos","Productos alimenticios de todo tipo",""),
        CategoriesItem(UUID.randomUUID().toString(),"Bebidas","Bebidas de todo tipo, alcoholicas como no alcoholicas",""),
        CategoriesItem(UUID.randomUUID().toString(),"Restaurantes","Ofertas de servicios de restaurante",""),
        CategoriesItem(UUID.randomUUID().toString(),"Ventas variadas","Ofertas de productos de todo tipo",""),
        CategoriesItem(UUID.randomUUID().toString(),"Belleza","Servicio de belleza y cuidado personal",""),
        CategoriesItem(UUID.randomUUID().toString(),"Tecnologia","Oferta de servicios y productos tecnologicos",""),
        CategoriesItem(UUID.randomUUID().toString(),"Mecanica","Arreglos y soluciones para vehiculos de todo tipo",""),
        CategoriesItem(UUID.randomUUID().toString(),"Piezas y partes","Venta de piezas para vehiculos, plantas electricas y de agua",""),
        CategoriesItem(UUID.randomUUID().toString(),"Ropa","Venta de ropas y prendas",""),
        CategoriesItem(UUID.randomUUID().toString(),"Vehiculos","Venta de vehiculos de todo tipo",""),
        CategoriesItem(UUID.randomUUID().toString(),"Paladar","Ofertas de alimentos variados para llevar",""),
        CategoriesItem(UUID.randomUUID().toString(),"Bares","Oferta de servicios de bares y ocio",""),
        CategoriesItem(UUID.randomUUID().toString(),"Pasajes","Transportaciones de pasajeros",""),
        CategoriesItem(UUID.randomUUID().toString(),"Carga","Transporte de carga",""),
        CategoriesItem(UUID.randomUUID().toString(),"Pizzerias","Venta de pizzas de diversos tipos",""),
        CategoriesItem(UUID.randomUUID().toString(),"Alquileres","Hospedaje y alquileres",""),
    ).map { CategoriesButtonItem(tittle = it.tittle, description = it.description, { selected -> categorySelected = selected }) }
*/
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when(deviceConfiguration) {
            DeviceConfiguration.SMART_CLOCK -> TODO()
            DeviceConfiguration.MOBILE_PORTRAIT,
            DeviceConfiguration.TABLET_PORTRAIT -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize().padding(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(
                            modifier = Modifier.size(10.dp)
                        )
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = null
                            )
                        }
                    }
                    TextField(
                        value = nameToFind,
                        onValueChange = {
                            nameToFind = it
                        },
                        textStyle = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                        colors = TextFieldDefaults.colors(
                            focusedLabelColor = MaterialTheme.colorScheme.inverseSurface,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colorScheme.inverseSurface
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = "Busque comida, eventos y lugares"
                            )
                        }
                    )
                   /* LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(categories) { category->
                            Button(
                                border = BorderStroke(
                                    1.dp,
                                    if(category.tittle == categorySelected) {
                                        MaterialTheme.colorScheme.inverseSurface
                                    } else {
                                        Color.Transparent
                                    }
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                                ),
                                onClick = {
                                    category.action(category.tittle)
                                }
                            ) {
                                Text(
                                    color = MaterialTheme.colorScheme.inverseSurface,
                                    text = category.tittle
                                )
                            }
                        }
                    }*/
                }
            }
            DeviceConfiguration.MOBILE_LANDSCAPE -> TODO()
            DeviceConfiguration.AUTOMOBILE_DEVICE -> TODO()
            DeviceConfiguration.TABLET_LANDSCAPE -> TODO()
            DeviceConfiguration.DESKTOP -> TODO()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun HomeInternalScreenPreview() {
    //Manejo de tamaños de pantallas
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowsSizeClass(windowSizeClass)
    HomeScreen(
        deviceConfiguration = deviceConfiguration,
        modifier = Modifier.fillMaxSize()
    )
}
