package com.example.crm.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm.R

@Composable
fun InicioSesion(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.imagen_entrada1),
            contentDescription = "Imagen de Entrada",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        )

        // Botones posicionados hacia la parte inferior
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Los coloca en la parte inferior
                .padding(horizontal = 24.dp, vertical = 32.dp), // Añadido padding para mayor espacio
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre los botones
        ) {
            Button(
                onClick = { navHostController.navigate("Login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF005BEA)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "Iniciar Sesion", color = Color.White, fontSize = 16.sp)
            }

            Button(
                onClick = { navHostController.navigate("Register") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF80C1FF)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "Registrarse", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
