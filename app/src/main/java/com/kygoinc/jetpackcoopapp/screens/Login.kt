package com.kygoinc.jetpackcoopapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kygoinc.jetpackcoopapp.R
import com.kygoinc.jetpackcoopapp.components.AppLogo
import com.kygoinc.jetpackcoopapp.components.BoldTextComponent
import com.kygoinc.jetpackcoopapp.components.LoginButtonComponent
import com.kygoinc.jetpackcoopapp.components.NormalTextComponent
import com.kygoinc.jetpackcoopapp.components.NormalTextFieldComponent
import com.kygoinc.jetpackcoopapp.components.PasswordTextFieldComponent
import com.kygoinc.jetpackcoopapp.viewmodels.AuthResult
import com.kygoinc.jetpackcoopapp.viewmodels.LoginViewModel


@Composable
fun Login(loginViewModel: LoginViewModel = viewModel(), navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // Observe changes in authResult
    val authResult = loginViewModel.authResult.value

    // State for displaying the Snackbar
    val snackbarVisibleState = remember { mutableStateOf(false) }


    // Trigger Snackbar visibility based on authResult
    LaunchedEffect(authResult) {
        if (authResult != null) {
            snackbarVisibleState.value = true
        }
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.dark_green))
    ) {




        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.dark_green))
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.n_dark_green)),
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    AppLogo(
                        Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.5f)
                            .padding(top = 20.dp)
                            .offset(y = 20.dp),
                    )

                    BoldTextComponent(
                        value = "Welcome to Co-op Bank", modifier = Modifier.offset(y = 20.dp)
                    )
                }

            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coop_building),
                    contentDescription = "Coop building",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.n_dark_green).copy(alpha = 0.95f)),


                    ) {


                    Column(

                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 80.dp, start = 24.dp, end = 24.dp),

                        ) {
                        NormalTextComponent(
                            value = "Use your credentials to log in",
                            modifier = Modifier
                                .align(Alignment.Start),

                            )

                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        NormalTextFieldComponent(
                            labelValue = "Username",
                            painterResource(id = R.drawable.person_icon),
                            onValueChange = { username = it }
                        )

                        PasswordTextFieldComponent(
                            labelValue = "Password",
                            painterResource(id = R.drawable.lock_icon),
                            onValueChange = { password = it },
                        )

                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        LoginButtonComponent(
                            username = username,
                            password = password,
                            loginViewModel = loginViewModel,
                            navController = navController
                        )

                        // Snackbar
                        if (snackbarVisibleState.value) {
                            Snackbar(
                                modifier = Modifier.padding(8.dp),
                                action = {
                                    TextButton(onClick = { snackbarVisibleState.value = false }) {
                                        Text("Dismiss")
                                    }
                                }
                            ) {
                                // Display a message based on the authentication result
                                when (authResult) {
                                    is AuthResult.Failure -> {
                                        Text("Authentication Failed: ${authResult.errorMessage}")

                                    }
                                    AuthResult.Success -> {
                                        Text("Login successful. Welcome, $username!")
                                        navController.navigate("welcomeDash/$username")

                                    }
                                    null -> Text("Authenticating...")
                                }
                            }


                        }
                    }
                }
            }
        }
    }
}




@Preview
@Composable
fun DefaultPreviewofLogin() {
    Login(navController = rememberNavController())
}