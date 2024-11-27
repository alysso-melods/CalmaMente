package com.example.calmamente

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calmamente.ui.theme.Poppins

@Composable
fun CalmamenteInicial(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xff353a40),
                    1f to Color(0xff353a40),
                    start = Offset(180f, 0f),
                    end = Offset(180f, 811f)
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.wallpaperfloresta),
            contentDescription = "Wallpaper do início",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(102.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(356.dp)
                    .height(229.dp)
            )

            Spacer(modifier = Modifier.height(292.dp))

            OutlinedButton(
                onClick = { navController.navigate("menu_principal") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff134803).copy(alpha = 0.85f)),
                border = BorderStroke(1.dp, Color(0xfffff5f5)),
                modifier = Modifier
                    .width(321.dp)
                    .height(64.dp)
            ) {
                Text(
                    text = "Iniciar",
                    color = Color(0xFFffffff),
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
