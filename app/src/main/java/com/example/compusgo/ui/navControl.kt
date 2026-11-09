
package UI

import RoomDB.DatabaseProvider
import RoomDB.StudentRepository
import ViweModel.LoginViewModel
import ViweModel.LoginViewModelFactory
import ViweModel.Status
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNav() {

    val navController = rememberNavController()
    val context = LocalContext.current

    val repository = remember {
        StudentRepository(
            DatabaseProvider.getDatabase(context).studentDao()
        )
    }

    val factory = remember {
        LoginViewModelFactory(repository)
    }

    val viewModel: LoginViewModel = viewModel(
        factory = factory
    )

    NavHost(
        navController = navController,
        startDestination = "splash",

        // New screen enters from right
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(550)
            )
        },

        // Previous screen leaves to left
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(550)
            )
        },

        // Back navigation
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(550)
            )
        },

        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(550)
            )
        }

    ) {

        composable("splash") {
            SplashScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("login") {
            LoginScreen(navController, viewModel)
        }

        composable("register") {
            RegisterUser(navController, viewModel)
        }

        composable("home") {
            if (Status.isLoggedIn) {
                HomeScreen(navController)
            } else {
                LoginScreen(navController, viewModel)
            }
        }
    }
}
