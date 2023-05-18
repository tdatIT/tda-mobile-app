package com.tda.app.view

import NavigationBottomBar
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.model.response.CategoryResp
import com.tda.app.ui.theme.black
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.viewmodel.CategoryAllViewModel

@Composable
fun CategoryScreen(navController: NavController) {
    val categoryAllViewModel = viewModel(modelClass = CategoryAllViewModel::class.java)
    val categories by categoryAllViewModel.state.collectAsState()
    Scaffold(topBar = {
        SecondAppBar(navController)
    }, bottomBar = {
        NavigationBottomBar(navController = navController)
    }) { padding ->
        Column(modifier = Modifier.padding(padding).background(ghost_white)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(categories) { _, item ->
                    CategoryCard(item, navController)
                }
            }
        }
    }


}

@Composable
fun CategoryCard(item: CategoryResp, navController: NavController) {
    Spacer(modifier = Modifier.width(16.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
            .clickable { navController.navigate("product_categoryCode/${item.code}") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = rememberAsyncImagePainter(item.images),
                    contentDescription = "",
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    Text(
                        text = item.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = black,
                        maxLines = 2
                    )
                    Text(
                        text = "Code: ${item.code.uppercase()}",
                        fontSize = 12.sp,
                        color = colorPrimary,
                    )
                }

                Icon(
                    imageVector = Icons.Rounded.Preview,
                    contentDescription = "",
                    tint = colorPrimary,
                    modifier = Modifier
                        .size(20.dp, 20.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewCategories() {
    val navController = rememberNavController()
    CategoryScreen(navController)
}

