package com.example.calmamente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
@Composable
fun AudioMindfulness(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(360.dp)
            .requiredHeight(811.dp)
            .background(brush = Brush.linearGradient(
                0.23f to Color(0xffa2e78d),
                0.5f to Color(0xff5b9749),
                1f to Color(0xff3c5f32),
                start = Offset(180f, 0f),
                end = Offset(180f, 811f)
            ))
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 91.dp, y = 30.dp)
                .requiredWidth(177.dp)
                .requiredHeight(114.dp)
        ) {
            Text(
                text = "Calmamente",
                color = Color(0xfffdfffb),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 30.1.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 6.68438720703125.dp, y = 69.81442260742188.dp)
                    .requiredWidth(164.dp)
                    .requiredHeight(36.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "logo2",
                modifier = Modifier
                    .requiredWidth(177.dp)
                    .requiredHeight(114.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.setavoltar),
            contentDescription = "setavoltar",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 30.dp, y = 59.dp)
                .requiredWidth(24.dp)
                .requiredHeight(35.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

        Image(
            painter = painterResource(id = R.drawable.medmindimage),
            contentDescription = "medmindimage",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 26.dp, y = 184.dp)
                .requiredWidth(311.dp)
                .requiredHeight(289.dp)
                .clip(shape = RoundedCornerShape(29.dp))
        )


        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 26.dp, y = 652.dp)
                .requiredSize(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.start),
                contentDescription = "Icon1",
                modifier = Modifier.requiredSize(30.dp)
            )
        }

        IconButton(
            onClick = {
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 307.dp, y = 652.dp)
                .requiredSize(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.start),
                contentDescription = "Icon2",
                modifier = Modifier.requiredSize(30.dp)
            )
        }

        Text(
            text = "Meditação Mindfulness",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = 0.dp, y = 498.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAudioMindfulness() {
    AudioMindfulness(navController = rememberNavController())
}