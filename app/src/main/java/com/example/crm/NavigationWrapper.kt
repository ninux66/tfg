package com.example.crm


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

import com.example.crm.Screens.InicioSesion
import com.example.crm.Screens.Login
import com.example.crm.Screens.Register



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWrapper (navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "InicioSesion") {

        composable ("InicioSesion") {InicioSesion(navHostController)}
        composable ("Login") {Login(navHostController)}
        composable ("Register") {Register(navHostController)}



    }
}























