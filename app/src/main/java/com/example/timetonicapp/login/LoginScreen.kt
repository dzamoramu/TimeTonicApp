package com.example.timetonicapp.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.timetonicapp.R
import com.example.timetonicapp.ui.theme.TimeTonicAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier,
    viewModel: LoginViewModel,
    navigationController: NavHostController,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Body(modifier = modifier, viewModel)
    }
}

@Composable
fun Body(modifier: Modifier, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val isLoginEnabled: Boolean by viewModel.isEnabled.observeAsState(false)
    Column(
        modifier = modifier.widthIn(max = 380.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.timetoniclogo),
            contentDescription = "TimeTonic Logo",
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = modifier.size(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Email(email = email, modifier = modifier) {
                viewModel.onLoginChanged(email = it, password = password)
            }
            Password(password = password, modifier = modifier) {
                viewModel.onLoginChanged(email = email, password = it)
            }
        }

        LoginButton(isLoginEnabled, viewModel)
    }
}

@Composable
fun Email(email: String, modifier: Modifier, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        modifier = modifier.widthIn(max = 280.dp),
        value = email,
        onValueChange = { onTextChanged(it) },
        label = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black
        )
    )
}

@Composable
fun Password(password: String, modifier: Modifier, onTextChanged: (String) -> Unit) {
    var passWordVisibility by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier.widthIn(max = 280.dp),
        value = password,
        onValueChange = { onTextChanged(it) },
        label = { Text(text = "Your Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black
        ),
        trailingIcon = {
            val image = if (passWordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passWordVisibility = !passWordVisibility }) {
                Icon(imageVector = image, contentDescription = "Show Password")
            }
        },
        visualTransformation = if (passWordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable

fun LoginButton(isLoginEnabled: Boolean, viewModel: LoginViewModel) {
    Button(
        onClick = { viewModel.onLoginClicked() },
        enabled = isLoginEnabled,
        modifier = Modifier.width(340.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
        Text(text = "Log in")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimeTonicAppTheme {
        Body(
            Modifier,
            LoginViewModel(LocalContext.current)
        )
    }
}