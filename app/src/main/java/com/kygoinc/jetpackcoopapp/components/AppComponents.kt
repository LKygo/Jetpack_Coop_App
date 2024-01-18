package com.kygoinc.jetpackcoopapp.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kygoinc.jetpackcoopapp.R
import com.kygoinc.jetpackcoopapp.viewmodels.AuthResult
import com.kygoinc.jetpackcoopapp.viewmodels.LoginViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.text.buildAnnotatedString as buildAnnotatedString


@Composable
fun NormalTextComponent(value: String, modifier: Modifier, style: TextStyle = TextStyle.Default) {
    Text(
        text = value,
        modifier
            .wrapContentSize()
            .padding(top = 14.dp, start = 12.dp),
        style = style.copy(
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),

    )
}

@Composable
fun IconHolderComponent(
    painterResource: Painter, modifier: Modifier, description: String
) {
    Icon(
        painter = painterResource,
        contentDescription = description,
        tint = Color.White,
        modifier = modifier
    )
}

@Composable
fun MultiColorWelcomeTextComponent(
    value: String, modifier: Modifier, style: TextStyle = TextStyle.Default
) {
    val textColor = colorResource(id = R.color.olive_green)

    Text(
        text = buildAnnotatedString {
            append("Welcome ")
            withStyle(style = SpanStyle(color = textColor, fontWeight = FontWeight.Bold)) {
                append(value)
            }
            append(" to the new Co-op Bank App")
        },
        modifier
            .fillMaxWidth()
            .padding(top = 14.dp, start = 12.dp),
        style = style.copy(
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun BoldTextComponent(value: String, modifier: Modifier) {
    Text(
        text = value,
        modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 22.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        ),
        color = colorResource(id = R.color.olive_green),
        textAlign = TextAlign.Center
    )
}

@Composable
fun AppLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.r_coop),
        contentDescription = "My image",
        modifier
            .heightIn(max = 160.dp)
            .widthIn(max = 160.dp)
            .clip(RoundedCornerShape(0.dp))
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTextFieldComponent(labelValue: String, painterResource: Painter, onValueChange: (String) -> Unit) {

    val usernameValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 12.dp)
        .padding(vertical = 4.dp, horizontal = 10.dp),
        value = usernameValue.value,
        onValueChange = {
            usernameValue.value = it
            onValueChange(it) },
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            cursorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
        ),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "person icon", tint = Color.White)

        }

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onValueChange: (String) -> Unit
) {

    val passwordValue = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 12.dp)
            .padding(vertical = 8.dp, horizontal = 10.dp),
        value = passwordValue.value,
        onValueChange = {
            passwordValue.value = it
            onValueChange(it)
        },
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            cursorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
        ),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "person icon", tint = Color.White)
        },
        trailingIcon = {
            if (!passwordVisibility.value) {
                Icon(
                    painter = painterResource(id = R.drawable.visibility_off_icon),
                    contentDescription = "visibility off",
                    tint = Color.White
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.visibility_icon),
                    contentDescription = "visibility on",
                    tint = Color.White
                )
            }
            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.visibility_icon),
                    contentDescription = "visibility on",
                    tint = Color.White
                )
            }
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()


    )

}

@Composable
fun NormalButtonComponent(
    onClick: () -> Unit,
    value: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = colorResource(id = R.color.olive_green),
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = value, fontSize = 18.sp, color = colorResource(id = R.color.dark_green)
            )
        }

    }
}
@Composable
fun LoginButtonComponent(
    username: String,
    password: String,
    loginViewModel: LoginViewModel,
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()

    // Observe changes in authResult
    val authResult = loginViewModel.authResult.value


    // Flag to indicate whether authentication is in progress
    var isAuthenticating by remember { mutableStateOf(false) }

    // Change isAuthenticating based on authResult changes
    LaunchedEffect(authResult) {
        when (authResult) {
            is AuthResult.Success, is AuthResult.Failure -> {
                isAuthenticating = false
            }
            null -> {
                // No response yet, keep isAuthenticating unchanged
            }


        }
    }

    Button(
        onClick = {

            // Reset the authResult state
            loginViewModel.resetAuthResult()


            if (!isAuthenticating) {
                isAuthenticating = true
                coroutineScope.launch {
                    try {
                        loginViewModel.authenticate(username, password, navController)
                        Log.d("Login", "$username, $password")
                    } catch (e: Exception) {
                        // Handle authentication errors gracefully
                        isAuthenticating = false // Allow retry on error
                        Log.d("Login", "Authenticating reset to false")
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = !isAuthenticating // Disable button during authentication
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = colorResource(id = R.color.olive_green),
                )
                .alpha(if (isAuthenticating) 0.7f else 1f), // Visual feedback
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isAuthenticating) "Authenticating..." else "Login",
                fontSize = 18.sp,
                color = colorResource(id = R.color.dark_green)
            )
        }
    }
}




//@Composable
//fun LoginButtonComponent(
//    username: String,
//    password: String,
//    loginViewModel: LoginViewModel
//) {
//    val coroutineScope = rememberCoroutineScope()
//    var isAuthenticating by remember { mutableStateOf(false) }
//
//    Button(
//        onClick = {
//            if (!isAuthenticating) {
//                isAuthenticating = true
//                coroutineScope.launch {
//                    try {
//                        loginViewModel.authenticate(username, password)
//                        Log.d("Login", "$username, $password")
//                    } catch (e: Exception) {
//                        // Handle authentication errors gracefully
//                        isAuthenticating = false // Allow retry on error
//                        Log.d("Login", "Authenicating reset to false")
//                    }
//                }
//            }
//        },
//        modifier = Modifier.fillMaxWidth(),
//        contentPadding = PaddingValues(8.dp),
//        colors = ButtonDefaults.buttonColors(Color.Transparent),
//        enabled = !isAuthenticating // Disable button during authentication
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(48.dp)
//                .background(
//                    color = colorResource(id = R.color.olive_green),
//                )
//                .alpha(if (isAuthenticating) 0.7f else 1f), // Visual feedback
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = if (isAuthenticating) "Authenticating..." else "Login",
//                fontSize = 18.sp,
//                color = colorResource(id = R.color.dark_green)
//            )
//        }
//    }
//}
