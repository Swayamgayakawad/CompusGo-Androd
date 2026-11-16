
package com.example.compusgo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import com.example.compusgo.R
import com.example.compusgo.ViweModel.BookStorViweModel

data class Book(
    val title: String,
    val price: Int,
    val image: Int
)

@Composable
fun BookStoreScreen(navController: NavHostController) {
    val bookViewModel: BookStorViweModel = viewModel()

    val books = listOf(
        Book("Record Book", 65, R.drawable.record_book),
        Book("Observation Book", 18, R.drawable.observation_book),
        Book("Assignment Book", 12, R.drawable.assignment_book),
        Book("Pen", 10, R.drawable.pen),
        Book("Pencil", 5, R.drawable.pencil),
        Book("ID Card Frame", 30, R.drawable.id_card_frame),
        Book("ID Card Tag", 30, R.drawable.id_card_tag)
    )

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

            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)

        ) {

            item(
                span = { GridItemSpan(2) },
                key = "book_header"
            ) {
                bookTop(navController)
            }

            items(
                items = books,
                key = { it.title }
            ) { book ->

                bookCard(
                    title = book.title,
                    price = book.price,
                    image = book.image,
                    viewModel = bookViewModel
                )
            }

            item(
                span = { GridItemSpan(2) },
                key = "book_footer"
            ) {
                bookBottom()
            }
        }
    }
}

@Composable
fun bookTop(navController: NavHostController) {

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
                text = "Campus Book Store 📚",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Button(
            onClick = {
                navController.navigate("book_cart")
            },
            shape = RoundedCornerShape(14.dp),
            contentPadding = PaddingValues(
                horizontal = 12.dp,
                vertical = 12.dp
            )
        ) {

            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Book Cart"
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text("Cart")
        }
    }
}



@Composable
fun bookCard(
    title: String,
    price: Int,
    image: Int,
    viewModel: BookStorViweModel
) {

    val quantity = viewModel.getQuantity(title)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),

        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .clip(RoundedCornerShape(12.dp)),

                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )

            Text(
                text = "₹$price",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Decrease quantity
                IconButton(
                    onClick = {
                        viewModel.decreaseQuantity(title)
                    },
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

                // Increase quantity
                IconButton(
                    onClick = {
                        viewModel.increaseQuantity(title)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase"
                    )
                }
            }

            Text(
                text = "Subtotal: ₹${price * quantity}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}



@Composable
fun bookBottom() {

    Text(
        text = "Happy Learning! 📖",

        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),

        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
