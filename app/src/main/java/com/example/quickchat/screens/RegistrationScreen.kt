package com.example.quickchat.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationScreen(){


    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        val email = rememberSaveable { mutableStateOf("") }
        val password = rememberSaveable { mutableStateOf("") }
        val confirmPassword = rememberSaveable { mutableStateOf("") }

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                //Logo
                Text(text = "")
            }
            Row() {
                //Email
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(start = 60.dp, end = 60.dp,bottom = 5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colors.primaryVariant),
                    leadingIcon = { Icon(Icons.Default.Person,contentDescription = "email",tint = MaterialTheme.colors.primary) },
                    label = { Text(text = "E-mail",color = MaterialTheme.colors.primaryVariant)}

                )
            }
            Row() {
                //Password
                OutlinedTextField(value = password.value, onValueChange = { password.value = it },
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(start = 60.dp, end = 60.dp,top = 5.dp,bottom = 3.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colors.primaryVariant),
                    leadingIcon = { Icon(Icons.Default.Lock,contentDescription = "password",tint = MaterialTheme.colors.primary) },
                    label = { Text(text = "Password",color = MaterialTheme.colors.primaryVariant)}

                )

            }
            Row() {
                //Confirm password
                OutlinedTextField(value = confirmPassword.value, onValueChange = { confirmPassword.value = it },
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(start = 60.dp, end = 60.dp,top = 5.dp,bottom = 3.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colors.primaryVariant),
                    leadingIcon = { Icon(Icons.Default.Lock,contentDescription = "password",tint = MaterialTheme.colors.primary)
                    },
                    label = { Text(text = "Confirm password",color = MaterialTheme.colors.primaryVariant)}

                )

            }

            Row() {
                //Log in
                Button(onClick = { /*TODO*/ },
                    Modifier
                        .padding(top = 30.dp)
                        .width(220.dp),shape = RoundedCornerShape(20.dp),) {
                    Text(text = "Sign up")
                }
            }



        }


    }

}