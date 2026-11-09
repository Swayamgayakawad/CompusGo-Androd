
package UI

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compusgo.R
import ViweModel.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current

    var text by remember { mutableStateOf("") }
    val appName = "compusGo"

    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.type)
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    LaunchedEffect(Unit) {

        // Typing animation
        for (i in appName.indices) {
            text = appName.substring(0, i + 1)

            mediaPlayer?.seekTo(0)
            mediaPlayer?.start()

            delay(180)
        }

        delay(800)

        // Check whether student data exists
        viewModel.checkUser { userExists ->

            if (userExists) {
                navController.navigate("home") {
                    popUpTo("splash") {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate("login") {
                    popUpTo("splash") {
                        inclusive = true
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 42.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "|",
                fontSize = 42.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
