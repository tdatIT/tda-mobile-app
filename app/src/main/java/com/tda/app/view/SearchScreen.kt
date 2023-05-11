package com.tda.app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.gray
import com.tda.app.ui.theme.light_gray
import com.tda.app.ui.theme.text_hint_color
import com.tda.app.ui.theme.white

@Composable
fun SearchScreen(nav: NavController) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(bgwhitelight)) {
        SearchAppBar(navController = nav)
        Text(
            text = "Tìm kiếm gần đây",
            Modifier
                .padding(start = 12.dp, top = 5.dp, bottom = 5.dp)
                .background(white),
            fontStyle = FontStyle.Italic
        )
        LazyColumn(modifier = Modifier.background(white)) {
            items(5) {

                Text(
                    text = "Sản phẩm 1",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(14.dp)
                )
                Divider(startIndent = 0.dp, thickness = 1.dp, color = text_hint_color)


            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSearchScreen() {
    val nav = rememberNavController()
    SearchScreen(nav = nav)
}