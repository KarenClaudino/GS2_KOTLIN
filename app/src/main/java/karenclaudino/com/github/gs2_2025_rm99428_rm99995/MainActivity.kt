package karenclaudino.com.github.gs2_2025_rm99428_rm99995

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karenclaudino.com.github.gs2_2025_rm99428_rm99995.ui.theme.AndroidComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("login") }
    var isAuthenticated by remember { mutableStateOf(false) }

    when {
        !isAuthenticated -> {
            LoginScreen(
                onLoginSuccess = {
                    isAuthenticated = true
                    currentScreen = "menu"
                }
            )
        }
        else -> {
            when (currentScreen) {
                "menu" -> MenuScreen(
                    onNavigateToIMC = { currentScreen = "imc" },
                    onNavigateToTeam = { currentScreen = "team" },
                    onLogout = {
                        isAuthenticated = false
                        currentScreen = "login"
                    }
                )
                "imc" -> IMCCalculatorScreen(
                    onBack = { currentScreen = "menu" }
                )
                "team" -> TeamScreen(
                    onBack = { currentScreen = "menu" }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black)
                .padding(24.dp)
                .width(300.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "LOGIN",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Usuário", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        placeholder = { Text("Digite seu usuário") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, Color.Black),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Senha", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Digite sua senha") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, Color.Black),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFCDD2))
                            .border(2.dp, Color(0xFFF44336))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = errorMessage,
                            color = Color(0xFFC62828),
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (username == "admin" && password == "123456") {
                            onLoginSuccess()
                        } else {
                            errorMessage = "usuário inválido"
                            username = ""
                            password = ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Entrar", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun MenuScreen(
    onNavigateToIMC: () -> Unit,
    onNavigateToTeam: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black)
                .padding(24.dp)
                .width(300.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "MENU",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Button(
                    onClick = onNavigateToIMC,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Cálculo de IMC", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onNavigateToTeam,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Equipe", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF44336),
                        contentColor = Color.White
                    )
                ) {
                    Text("Voltar", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun IMCCalculatorScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Pair<Double, String>?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black)
                .padding(24.dp)
                .width(300.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                if (result == null) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Seu nome", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = { Text("Digite seu nome") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(2.dp, Color.Black),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Seu Peso (kg)", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                        TextField(
                            value = weight,
                            onValueChange = { weight = it },
                            placeholder = { Text("Digite seu peso") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(2.dp, Color.Black),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Sua altura (m)", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                        TextField(
                            value = height,
                            onValueChange = { height = it },
                            placeholder = { Text("Digite sua altura (ex: 1.75)") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(2.dp, Color.Black),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (name.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty()) {
                                val w = weight.toDoubleOrNull() ?: 0.0
                                val h = height.toDoubleOrNull() ?: 0.0
                                if (w > 0 && h > 0) {
                                    val imc = w / (h * h)
                                    result = Pair(Math.round(imc * 100.0) / 100.0, name)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Calcular", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onBack,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Voltar", fontSize = 16.sp)
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFBBDEFB))
                            .border(2.dp, Color(0xFF1976D2))
                            .padding(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Resultado", fontSize = 20.sp, modifier = Modifier.padding(bottom = 12.dp))
                            Text("Nome: ${result!!.second}", fontSize = 16.sp)
                            Text("IMC: ${result!!.first}", fontSize = 16.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            result = null
                            name = ""
                            weight = ""
                            height = ""
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Calcular Novamente", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onBack,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Voltar", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun TeamScreen(onBack: () -> Unit) {
    val teamMembers = listOf(
        "Isabelle Serrano Furman",
        "Karen Claudino"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black)
                .padding(24.dp)
                .width(300.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Equipe",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black)
                        .padding(12.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Integrantes", fontSize = 16.sp, modifier = Modifier.padding(bottom = 12.dp))
                        teamMembers.forEach { member ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .border(2.dp, Color(0xFFCCCCCC))
                                    .padding(12.dp)
                                    .padding(bottom = 8.dp)
                            ) {
                                Text(member, fontSize = 14.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    )
                ) {
                    Text("Voltar", fontSize = 16.sp)
                }
            }
        }
    }
}
