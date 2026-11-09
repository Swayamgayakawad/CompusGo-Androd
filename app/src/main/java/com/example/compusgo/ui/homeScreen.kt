package UI

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compusgo.ui.theme.CompusGoTheme


@Composable
fun HomeScreen(navController: NavHostController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Top()

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Welcome section
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(
                    animationSpec = tween(500)
                ) + slideInVertically(
                    initialOffsetY = { 30 },
                    animationSpec = tween(500)
                )
            ) {

                Column {

                    Text(
                        text = "Good Morning 👋",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Welcome back to your campus",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.65f
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            TimeTabel()

            Body()

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}


@Composable
fun Top() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.size(48.dp),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {

                Text(
                    text = "🎓",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    fontSize = 24.sp
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Text(
                text = "CompusGo",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        IconButton(
            onClick = {
                // More menu
            }
        ) {

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun TimeTabel() {

    val schedule = listOf(
        Schedule("Data Structures", "9:00 AM", "Room 101"),
        Schedule("DBMS", "10:00 AM", "Lab 2"),
        Schedule("Operating System", "11:00 AM", "Room 203"),
        Schedule("Computer Networks", "1:00 PM", "Room 105"),
        Schedule("Software Engineering", "2:00 PM", "Lab 1"),
        Schedule("Project", "3:00 PM", "Project Lab")
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "Today's Schedule",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(schedule) { item ->

                    ScheduleItem(
                        subject = item.subject,
                        time = item.time,
                        room = item.room,
                        isCurrent = item.subject == "Data Structures"
                    )
                }
            }
        }
    }
}


@Composable
fun ScheduleItem(
    subject: String,
    time: String,
    room: String,
    isCurrent: Boolean
) {

    Card(
        modifier = Modifier.width(180.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrent)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            if (isCurrent) {

                Text(
                    text = "CURRENT",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )
            }

            Text(
                text = time,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = subject,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = room,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                    alpha = 0.7f
                )
            )
        }
    }
}


@Composable
fun Body() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {

        Text(
            text = "Quick Access",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            QuickAccessCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Percent,
                title = "Attendance"
            )

            QuickAccessCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.QrCode2,
                title = "QR Code"
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            QuickAccessCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.MenuBook,
                title = "Book Store"
            )

            QuickAccessCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Fastfood,
                title = "Canteen"
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            QuickAccessCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Map,
                title = "College Map"
            )

            QuickAccessCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.CalendarMonth,
                title = "Calendar"
            )
        }
    }
}


@Composable
fun QuickAccessCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String
) {

    var pressed = false

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.94f else 1f,
        animationSpec = tween(100),
        label = "cardScale"
    )

    Card(
        modifier = modifier
            .height(125.dp)
            .scale(scale)
            .clickable {
                pressed = !pressed
            },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomePreview() {

    val navController = rememberNavController()

    CompusGoTheme {

        HomeScreen(
            navController = navController
        )
    }
}




data class Schedule(
    val subject: String,
    val time: String,
    val room: String
)