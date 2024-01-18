package com.kygoinc.jetpackcoopapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kygoinc.jetpackcoopapp.R
import com.kygoinc.jetpackcoopapp.components.AppLogo
import com.kygoinc.jetpackcoopapp.components.IconHolderComponent
import com.kygoinc.jetpackcoopapp.components.LogoutTextComponent
import com.kygoinc.jetpackcoopapp.components.MultiColorWelcomeTextComponent


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WelcomeDash(username: String, navController: NavHostController) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.n_dark_green))
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {


                IconHolderComponent(
                    painterResource(id = R.drawable.ic_logout),
                    modifier = Modifier.padding(top = 20.dp),
                    description = "Logout"
                )
                LogoutTextComponent(
                    value = "Logout",
                    modifier = Modifier.wrapContentSize(),
                    navController = navController
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxHeight(0.25f)
                    .fillMaxWidth()
                    .padding()
                    .background(color = colorResource(id = R.color.n_dark_green)),
                contentAlignment = Alignment.Center
            ) {

                AppLogo(
                    Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f),
                )

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
                    MultiColorWelcomeTextComponent(
                        value = username,
                        modifier = Modifier.padding(vertical = 20.dp),

                        )
                }
            }
        }
    }

}


@Preview
@Composable
fun PreviewWelcomeDash() {
    WelcomeDash(username = "User", navController = rememberNavController())
}