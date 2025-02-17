package com.example.crm.Screens

import android.util.Patterns
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navHostController: NavHostController) {
    var emailOrPhone by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordVisible by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    // Lógica para manejar el inicio de sesión
    val loginUser = {
        if (emailOrPhone.text.isNotEmpty() && password.text.isNotEmpty()) {
            // Validar formato de email o teléfono
            val emailValid = Patterns.EMAIL_ADDRESS.matcher(emailOrPhone.text).matches()
            val phoneValid = Patterns.PHONE.matcher(emailOrPhone.text).matches()

            if (emailValid || phoneValid) {
                auth.signInWithEmailAndPassword(emailOrPhone.text, password.text)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            Log.d("Login", "Inicio de sesión exitoso: ${user?.email}")
                            // Navegar a la pantalla de inicio
                            navHostController.popBackStack() // Opcional, para evitar volver atrás
                            navHostController.navigate("Home")
                        } else {
                            // Manejo de errores de inicio de sesión
                            Log.e("Login", "Error al iniciar sesión: ${task.exception?.message}")
                            println("Error al iniciar sesión: ${task.exception?.message}")
                        }
                    }
            } else {
                // Muestra un mensaje de error si no es un correo ni un teléfono válido
                println("Formato de correo electrónico o teléfono inválido.")
            }
        } else {
            println("Por favor, complete todos los campos.")
        }
    }

    Spacer(modifier = Modifier.height(60.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Encabezado con flecha y título
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { navHostController.navigate("InicioSesion") }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }

            Text(
                text = "Iniciar Sesion",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF005BEA),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Mensaje de bienvenida
        Text(
            text = "Bienvenido",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF005BEA),
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        // Campos de texto
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = emailOrPhone,
                onValueChange = { emailOrPhone = it },
                label = { Text("Gmail o Telefono") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFF3F3F3),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF005BEA)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFF3F3F3),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF005BEA)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                shape = RoundedCornerShape(12.dp)
            )
        }

        // Link de olvidó su contraseña
        TextButton(
            onClick = { /* TODO: Navegar a recuperar contraseña */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = "He olvidado mi contraseña",
                fontSize = 14.sp,
                color = Color(0xFF005BEA)
            )
        }

        // Botón de inicio de sesión
        Button(
            onClick = { loginUser() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF005BEA)),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Iniciar Sesion", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de registro
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "No tienes cuenta? ",
                fontSize = 14.sp,
                color = Color.Gray
            )
            TextButton(
                onClick = { navHostController.navigate("Register") },
            ) {
                Text(
                    text = "Registrate",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF005BEA)
                )
            }
        }
    }
}
