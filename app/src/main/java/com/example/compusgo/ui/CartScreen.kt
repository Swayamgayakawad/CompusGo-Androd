package com.example.compusgo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compusgo.viewmodel.CartViewModel

@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel
) {

    val cartItems = cartViewModel.cartItems

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                Text(
                    text = "Your Cart 🛒",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            if (cartItems.isEmpty()) {

                item {
                    Text(
                        text = "Your cart is empty!",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Button(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("Continue Shopping")
                    }
                }

            } else {

                items(
                    items = cartItems,
                    key = { it.food.name }
                ) { item ->

                    ItemCard(
                        foodname = item.food.name,
                        price = item.food.price,
                        image = item.food.image,
                        quantity = item.quantity,

                        onIncrease = {
                            cartViewModel.increaseQuantity(item.food)
                        },

                        onDecrease = {
                            cartViewModel.decreaseQuantity(item.food)
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Total: ₹${cartViewModel.getTotalPrice()}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            navController.navigate("checkout")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Place Order")
                    }

                    TextButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Continue Shopping")
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    foodname: String,
    price: Int,
    image: Int,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = foodname,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = foodname,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "₹$price",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(
                        onClick = onDecrease,
                        contentPadding = PaddingValues(
                            horizontal = 12.dp
                        )
                    ) {
                        Text("-")
                    }

                    Text(
                        text = "$quantity",
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Button(
                        onClick = onIncrease,
                        contentPadding = PaddingValues(
                            horizontal = 12.dp
                        )
                    ) {
                        Text("+")
                    }
                }

                Text(
                    text = "Subtotal: ₹${price * quantity}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
