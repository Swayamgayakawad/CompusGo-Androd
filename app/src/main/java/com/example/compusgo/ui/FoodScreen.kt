package com.example.compusgo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compusgo.ViewModel.foods
import com.example.compusgo.viewmodel.CartViewModel

@Composable
fun FoodScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp),

            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Header
            item(
                span = { GridItemSpan(2) },
                key = "header"
            ) {
                top(navController)
            }

            // Food list
            items(
                items = foods,
                key = { food -> food.name }
            ) { food ->

                FoodCard(
                    foodname = food.name,
                    price = food.price,
                    image = food.image,
                    onAddToCart = {
                        cartViewModel.addToCart(food)
                    }
                )
            }

            // Footer
            item(
                span = { GridItemSpan(2) },
                key = "footer"
            ) {
                bottom()
            }
        }
    }
}

@Composable
fun top(navController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                text = "Welcome to",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Our Canteen 🍔",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Button(
            onClick = {
                navController.navigate("cart")
            },
            shape = RoundedCornerShape(14.dp),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 12.dp
            )
        ) {

            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Cart")
        }
    }
}

@Composable
fun FoodCard(
    foodname: String,
    price: Int,
    image: Int,
    onAddToCart: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = foodname,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = foodname,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = "₹$price",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 4.dp,
                    vertical = 6.dp
                )
            ) {
                Text("Add to Cart")
            }
        }
    }
}

@Composable
fun bottom() {
    Spacer(
        modifier = Modifier.height(20.dp)
    )
}