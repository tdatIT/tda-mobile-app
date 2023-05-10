package com.tda.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tda.app.ui.theme.TdaMobilemobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TdaMobilemobileTheme {
                ////////
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TdaMobilemobileTheme{
                        LoginUiMain()
                    }
                }
            }
        }
    }
}

@Composable
fun LoginUiMain() {
    TdaMobilemobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Navigation()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TdaMobilemobileTheme {
        LoginUiMain()
    }
}