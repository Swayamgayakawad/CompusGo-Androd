
package com.example.compusgo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.example.compusgo.ViweModel.BookStorViweModel

// 1. Main Screen
@Composable
fun BookCartScreen(
    navController: NavHostController,
    viewModel: BookStorViweModel
) {

    val cartBooks = campusBooks.filter {
        viewModel.getQuantity(it.title) > 0
    }

    val totalPrice = cartBooks.sumOf {
        it.price * viewModel.getQuantity(it.title)
    }

    Scaffold(
        topBar = {
            BookCartTopBar(navController)
        },

        bottomBar = {
            if (cartBooks.isNotEmpty()) {
                BookCartBottomBar(totalPrice)
            }
        }

    ) { paddingValues ->

        if (cartBooks.isEmpty()) {

            EmptyBookCart(navController)

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {

                items(
                    items = cartBooks,
                    key = { it.title }
                ) { book ->

                    BookCartItem(
                        book = book,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@Composable
fun BookCartTopBar(
    navController: NavHostController
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        Text(
            text = "My Book Cart",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}


// 3. Empty Cart
@Composable
fun EmptyBookCart(
    navController: NavHostController
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Your Cart is Empty 🛒",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Add some stationery to get started!")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Continue Shopping")
            }
        }
    }
}


// 4. Individual Cart Item
@Composable
fun BookCartItem(
    book: Book,
    viewModel: BookStorViweModel
) {

    val quantity = viewModel.getQuantity(book.title)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(book.image),
                contentDescription = book.title,

                modifier = Modifier
                    .size(85.dp)
                    .clip(RoundedCornerShape(10.dp)),

                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text("₹${book.price} each")

                Text(
                    text = "Subtotal: ₹${book.price * quantity}",
                    color = MaterialTheme.colorScheme.primary
                )

                BookQuantityControl(
                    quantity = quantity,

                    onIncrease = {
                        viewModel.increaseQuantity(book.title)
                    },

                    onDecrease = {
                        viewModel.decreaseQuantity(book.title)
                    }
                )
            }
        }
    }
}


// 5. Quantity Controls
@Composable
fun BookQuantityControl(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = onDecrease,
            enabled = quantity > 0
        ) {

            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease"
            )
        }

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.titleMedium
        )

        IconButton(
            onClick = onIncrease
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase"
            )
        }
    }
}


// 6. Bottom Total and Order
@Composable
fun BookCartBottomBar(
    totalPrice: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),

        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Total Amount",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "₹$totalPrice",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    // TODO: Navigate to checkout
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Place Order")
            }
        }
    }
}
