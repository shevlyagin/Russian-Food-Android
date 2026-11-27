package com.shevlyagin.food_pmm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shevlyagin.food_pmm.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MyScaffold() {

    var index by rememberSaveable {
        mutableIntStateOf(0)
    }

    var foodIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = { MyTopAppBar() },

        bottomBar = {
            MyBottomNavigation(index) {
                index = it
            }
        },

        floatingActionButtonPosition = FabPosition.End,

        floatingActionButton = {
            if (index == 0) {
                MyFAB {
                    foodIndex = (foodIndex + 1) % 3
                }
            }
        },

        content = { innerPadding ->
            MyContent(innerPadding, index, foodIndex)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Russian Food",
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF8B2525),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun MyBottomNavigation(
    index: Int,
    onSelect: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFFF2DFCC)
    ) {

        NavigationBarItem(
            selected = index == 0,
            onClick = { onSelect(0) },
            icon = {
                Icon(Icons.Default.Home, "Food")
            },
            label = { Text("Food") }
        )

        NavigationBarItem(
            selected = index == 1,
            onClick = { onSelect(1) },
            icon = {
                Icon(Icons.Default.Info, "About")
            },
            label = { Text("About") }
        )
    }
}

@Composable
fun MyContent(
    innerPadding: PaddingValues,
    index: Int,
    foodIndex: Int
) {
    val names = listOf(
        "Borscht",
        "Pelmeni",
        "Blini"
    )

    val images = listOf(
        R.drawable.borscht,
        R.drawable.pelmeni,
        R.drawable.blini
    )

    val descriptions = listOf(
        "Traditional beetroot soup.",
        "Traditional dumplings filled with meat.",
        "Traditional thin pancakes."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (index == 0) {

            Text(
                text = names[foodIndex],
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(
                    id = images[foodIndex]
                ),
                contentDescription = names[foodIndex],
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Text(
                text = descriptions[foodIndex],
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

        } else {

            Text(
                text = "About",
                fontSize = 30.sp
            )

            Text(
                text = "Russian cuisine is known for its traditional soups, dumplings and pancakes.",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun MyFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFF8B2525),
        contentColor = Color.White
    ) {
        Icon(
            Icons.Default.ArrowForward,
            contentDescription = "Next food"
        )
    }
}