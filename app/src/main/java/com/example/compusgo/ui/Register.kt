package UI

import ViweModel.LoginViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.text.KeyboardOptions


@Composable
fun RegisterUser(
    NavControl: NavHostController,
    viewModel: LoginViewModel
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Head()

            Spacer(modifier = Modifier.height(28.dp))

            UserData(viewModel,NavControl)
        }
    }
}


@Composable
fun Head() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Create Account",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Create your CompusGo student account",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun UserData(viewModel: LoginViewModel,navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // USN
        OutlinedTextField(
            value = viewModel.USN,
            onValueChange = {
                viewModel.onUSNChange(it)
            },
            label = {
                Text("USN")
            },
            placeholder = {
                Text("Enter your USN")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))


        // Name
        OutlinedTextField(
            value = viewModel.Name,
            onValueChange = {
                viewModel.onNameChange(it)
            },
            label = {
                Text("Name")
            },
            placeholder = {
                Text("Enter your name")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))


        // Phone
        OutlinedTextField(
            value = viewModel.Phone,
            onValueChange = {
                viewModel.onPhoneChange(it)
            },
            label = {
                Text("Phone Number")
            },
            placeholder = {
                Text("Enter your phone number")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))


        // Password
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = {
                viewModel.onPasswordChange(it)
            },
            label = {
                Text("Password")
            },
            placeholder = {
                Text("Create a password")
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))


        // Confirm Password
        OutlinedTextField(
            value = viewModel.ConfirmPassword,
            onValueChange = {
                viewModel.onConfirmPasswordChange(it)
            },
            label = {
                Text("Confirm Password")
            },
            placeholder = {
                Text("Re-enter your password")
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (viewModel.registrationStatus.isNotEmpty()) {
            Text(
                text = viewModel.registrationStatus,
                color = if (viewModel.registrationStatus.contains("successfully", ignoreCase = true)) 
                    MaterialTheme.colorScheme.primary 
                else 
                    MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Register Button
        Button(
            onClick = {
                viewModel.registerUser(
                    onSuccess = {
                        navController.navigate("login") {
                            popUpTo("register") {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        ) {
            Text("Create Account")
        }

        Spacer(modifier = Modifier.height(12.dp))



    }
}
