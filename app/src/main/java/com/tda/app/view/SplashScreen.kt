package com.tda.app.view

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R

import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.utils.Constants
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(2f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    overshootInterpolator.getInterpolation(it)
                }
            )
        )
        delay(Constants.SPLASH_SCREEN_DURATION)
        //navController.popBackStack()
        navController.navigate(Screen.HomeScreen.route)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = ColorPainter(colorPrimary),
            contentDescription = "TDA bg",
            contentScale = ContentScale.FillWidth
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.tda_logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop
            )
            Text(
                text = "TDA Laptop",
                fontSize = 32.sp,
                fontWeight = FontWeight(600),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp),
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
fun previewScreen() {
    val nav = rememberNavController()
    SplashScreen(navController = nav)
}