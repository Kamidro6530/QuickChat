package com.example.quickchat.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(){

    Column( Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ) {
        Row() {
            //Welcome
            Text(text = "Welcome to ",fontSize = 26.sp,fontWeight = FontWeight.Bold)
        }
        Row() {
           //Logo
        }
        Row() {
            //Log in button
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 40.dp)
                    .width(220.dp),shape = RoundedCornerShape(20.dp),) {
                Text(text = "Login")
            }
        }
        Row() {
            //Sign up
            Button(onClick = { /*TODO*/ },
                Modifier
                    .padding(top = 20.dp)
                    .width(220.dp),shape = RoundedCornerShape(20.dp),) {
                Text(text = "Sign up")
            }
        }
    }
}