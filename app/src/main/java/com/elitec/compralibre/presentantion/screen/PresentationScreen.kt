package com.elitec.compralibre.presentantion.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elitec.compralibre.R
import com.elitec.compralibre.presentantion.util.OnboardingItem
import kotlinx.coroutines.launch

@Composable
fun PresentationScreen(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Lista de elementos del carrusel
    val onboardingItems = listOf(
        OnboardingItem(
            imageRes = R.drawable.undraw_mobile_search_macy_gray, // Reemplaza con tu drawable
            title = "Descubre Servicios Locales",
            description = "Encuentra los mejores bienes y servicios en tu ciudad fácilmente."
        ),
        OnboardingItem(
            imageRes = R.drawable.undraw_road_sign_navigation_gray,
            title = "Navega con Confianza",
            description = "Explora tu ciudad con actualizaciones de ubicación en tiempo real."
        ),
        OnboardingItem(
            imageRes = R.drawable.undraw_showing_support_comunity_gray,
            title = "Conecta con tu Comunidad",
            description = "Apoya a negocios locales y mantente conectado."
        )
    )

    // Estado del carousel
    val pagerState = rememberPagerState(pageCount = { onboardingItems.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón de Omitir
        TextButton(
            onClick = onFinish,
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text("Omitir", color = MaterialTheme.colorScheme.primary)
        }

        // Carrusel con HorizontalPager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val item = onboardingItems[page]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Ilustración estilo unDraw
                Image(
                    painter = painterResource(item.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 16.dp)
                )
                // Título
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Descripción
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }

        // Indicadores de página
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(onboardingItems.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(2.dp)
                        .background(
                            color = if (pagerState.currentPage == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                            },
                            shape = MaterialTheme.shapes.small
                        )
                        .animateContentSize() // Animación suave en los indicadores
                )
            }
        }

        // Botón de Siguiente/Finalizar
        Button(
            onClick = {
                if (pagerState.currentPage < onboardingItems.size - 1) {
                    // Mover a la siguiente página
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onFinish()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(if (pagerState.currentPage == onboardingItems.size - 1) "Comenzar" else "Siguiente")
        }
    }
}