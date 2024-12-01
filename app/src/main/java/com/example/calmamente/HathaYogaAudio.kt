package com.example.calmamente

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calmamente.ui.theme.Poppins

@Composable
fun HathaYogaAudios(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var isLooping by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
    var duration by remember { mutableFloatStateOf(0f) }
    var isMuted by remember { mutableStateOf(false) }
    var volume by remember { mutableFloatStateOf(0.5f) }
    var showSlider by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        mediaPlayer = MediaPlayer.create(context, R.raw.hathaaudio).apply {
            setOnCompletionListener {
                isPlaying = false
            }
            duration.let { duration = it }
        }
    }

    LaunchedEffect(mediaPlayer?.isPlaying) {
        while (isPlaying) {
            mediaPlayer?.let {
                progress = it.currentPosition.toFloat() / it.duration.toFloat()
            }
            kotlinx.coroutines.delay(100)
        }
    }

    fun togglePlayPause() {
        mediaPlayer?.let {
            if (isPlaying) {
                it.pause()
            } else {
                it.start()
            }
            isPlaying = !isPlaying
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(
                0f to Color(0xffa2e78d),
                0.63f to Color(0xff5b9749),
                1f to Color(0xff3c5f32),
                start = Offset(180f, 0f),
                end = Offset(180f, 811f))
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.hathaimg),
            contentDescription = "hatha yoga",
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-100).dp)
                .requiredWidth(311.dp)
                .requiredHeight(289.dp)
                .clip(shape = RoundedCornerShape(29.dp))
        )

        Text(
            text = "Hatha Yoga",
            color = Color.White,
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp, y = 490.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = 0.dp, y = 550.dp)
                .width(350.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .requiredHeight(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = progress)
                        .requiredHeight(10.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-170).dp)
                .fillMaxWidth()
                .requiredHeight(61.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back10),
                    contentDescription = "back10",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 110.dp)
                        .requiredSize(30.dp)
                        .clickable {
                            mediaPlayer?.let {
                                val newPosition = (it.currentPosition - 10000).coerceAtLeast(0)
                                it.seekTo(newPosition)
                            }
                        }
                )

                Image(
                    painter = painterResource(id = if (isPlaying) R.drawable.pauseicon else R.drawable.starticon),
                    contentDescription = if (isPlaying) "Pause Icon" else "Start Icon",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .requiredWidth(60.dp)
                        .requiredHeight(60.dp)
                        .clickable { togglePlayPause() }
                )

                Image(
                    painter = painterResource(id = R.drawable.front10),
                    contentDescription = "front10",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-110).dp)
                        .requiredSize(30.dp)
                        .clickable {
                            mediaPlayer?.let {
                                val newPosition = (it.currentPosition + 10000).coerceAtMost(it.duration)
                                it.seekTo(newPosition)
                            }
                        }
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.setavoltar),
            contentDescription = "setavoltar",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 32.dp, y = 84.dp)
                .requiredWidth(24.dp)
                .requiredHeight(20.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

        Image(
            painter = painterResource(id = if (isLooping) R.drawable.loopaudioatvd else R.drawable.loopaudio),
            contentDescription = if (isLooping) "Looping Ativado" else "Looping Desativado",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 32.dp, y = 640.dp)
                .requiredSize(32.dp)
                .clickable {
                    isLooping = !isLooping
                    mediaPlayer?.isLooping = isLooping
                }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = if (isMuted) R.drawable.audiovolumemute else R.drawable.audiovolume),
                contentDescription = if (isMuted) "Mute Volume" else "Audio Volume",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 328.dp, y = 640.dp)
                    .requiredSize(32.dp)
                    .clickable {
                        showSlider = !showSlider
                    }
            )
            if (showSlider) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 272.dp, y = 680.dp)
                ) {
                    Slider(
                        value = volume,
                        onValueChange = { newVolume ->
                            volume = newVolume
                            mediaPlayer?.setVolume(volume, volume)
                            isMuted = volume == 0f
                        },
                        valueRange = 0f..1f,
                        modifier = Modifier
                            .height(140.dp)
                            .width(140.dp)
                            .rotate(270f),
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = Color.White,
                            inactiveTrackColor = Color.Gray
                        ))
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "logo",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .requiredWidth(177.dp)
                .requiredHeight(114.dp)
                .offset(y = 50.dp)
        )
    }
}
@Preview
@Composable
fun HathaYogaSom() {
    HathaYogaAudios(navController = rememberNavController())
}