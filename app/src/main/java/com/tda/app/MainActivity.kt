package com.tda.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tda.app.ui.theme.Final_project_mobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Final_project_mobileTheme {
                ////////
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Final_project_mobileTheme{
                        LoginUiMain()
                    }
                }
            }
        }
    }
}

@Composable
fun LoginUiMain() {
    Final_project_mobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Navigation()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Final_project_mobileTheme {
        LoginUiMain()
    }
}