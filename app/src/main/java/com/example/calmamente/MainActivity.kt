package com.example.calmamente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calmamente.ui.theme.CalmaMenteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmaMenteTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            CalmamenteInicial(navController = navController)
        }
        composable("menu_principal") {
            Home(navController = navController)
        }
        composable("mindfulness_audio") {
            AudioMindfulness(navController = navController)
        }
        composable("meditacao_guiada") {
            MedGuiadaAudio(navController = navController)
        }
        composable("meditacao_som") {
            MeditacaoSom(navController = navController)
        }
        composable("yin_yoga") {
            YinYogaAudios(navController = navController)
        }
        composable("hatha_yoga") {
            HathaYogaAudios(navController = navController)
        }
    }
}






