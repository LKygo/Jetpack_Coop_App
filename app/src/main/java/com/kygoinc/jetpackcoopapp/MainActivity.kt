package com.kygoinc.jetpackcoopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kygoinc.jetpackcoopapp.screens.Login
import com.kygoinc.jetpackcoopapp.screens.WelcomeDash
import com.kygoinc.jetpackcoopapp.ui.theme.JetpackCoopAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCoopAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LoginApplication()
                }
            }
        }
    }
}

@Composable
fun LoginApplication() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login", content = { Login(navController = navController) })
        composable("welcomeDash/{username}", content = { backsStackEntry ->
            WelcomeDash(
                username = backsStackEntry.arguments?.getString("username") ?: "",
                navController = navController
            )
        })
    })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackCoopAppTheme {
        Login(navController = rememberNavController())
    }
}