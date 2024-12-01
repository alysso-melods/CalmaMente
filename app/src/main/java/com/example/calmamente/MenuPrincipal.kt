package com.example.calmamente

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.calmamente.ui.theme.Poppins
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class SentimentosItem(val imageRes: Int, val label: String)

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val showGrid = remember { mutableStateOf(false) }
    val selectedIndices = remember { mutableStateListOf<Int>() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff649b53))
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Logo()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Como você está se sentindo hoje?",
                color = Color.White,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            if (!showGrid.value) {
                RegistroHumor(
                    onClick = { showGrid.value = true }
                )
            } else {
                val gridData = listOf(
                    SentimentosItem(R.drawable.feliz, "Alegre"),
                    SentimentosItem(R.drawable.triste, "Triste"),
                    SentimentosItem(R.drawable.relax, "Relaxado"),
                    SentimentosItem(R.drawable.cansado, "Cansado"),
                    SentimentosItem(R.drawable.pacifico, "Pacífico"),
                    SentimentosItem(R.drawable.furioso, "Furioso"),
                    SentimentosItem(R.drawable.calmo, "Calmo"),
                    SentimentosItem(R.drawable.anxious, "Ansioso"),
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(gridData) { item ->
                        SentimentoButton(
                            imageRes = item.imageRes,
                            label = item.label,
                            isSelected = selectedIndices.contains(gridData.indexOf(item)),
                            onClick = {
                                val index = gridData.indexOf(item)
                                if (selectedIndices.contains(index)) {
                                    selectedIndices.remove(index)
                                } else {
                                    selectedIndices.add(index)
                                }
                            }
                        )
                    }
                }
            }

            val navigator = navController

            Spacer(modifier = Modifier.height(20.dp))
            Recomendacoes()
            Spacer(modifier = Modifier.height(20.dp))
            Mindfulness(modifier = Modifier, navigator)
            Spacer(modifier = Modifier.height(20.dp))
            MedGuiada(modifier = Modifier, navigator)
            Spacer(modifier = Modifier.height(20.dp))
            MedSom(modifier = Modifier, navigator)
            Spacer(modifier = Modifier.height(20.dp))
            YinYoga(modifier = Modifier, navigator)
            Spacer(modifier = Modifier.height(20.dp))
            HathaYoga(modifier = Modifier, navigator)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun RegistroHumor(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(321.dp)
            .height(64.dp)
    ) {
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff134803).copy(alpha = 0.85f)),
            border = BorderStroke(1.dp, Color(0xfffff5f5)),
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Registro do Humor",
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SentimentoButton(
    imageRes: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (isSelected) Color(0xFF134803) else Color.White.copy(alpha = 0.2f))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Imagem dos sentimentos",
                modifier = Modifier.size(39.dp)
            )
        }
        Text(
            text = label,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .width(66.dp)
                .padding(top = 4.dp)
        )
    }
}

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(177.dp)
            .height(114.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Logo",
            modifier = Modifier.size(180.dp)
        )
    }
}

@Composable
fun Recomendacoes(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 313.dp)
            .requiredHeight(height = 28.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.linha),
            contentDescription = "linha",
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp, y = 28.dp)
                .requiredWidth(width = 313.dp)
                .border(border = BorderStroke(1.dp, Color.White))
        )
        Text(
            text = "RECOMENDAÇÕES",
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
        )
    }
}

@Composable
fun Mindfulness(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .requiredWidth(width = 312.dp)
            .requiredHeight(height = 150.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 320.dp)
                .requiredHeight(height = 160.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xfff7f3f0))
        )
        Text(
            text = "Meditação Mindfulness",
            color = Color(0xff253334),
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 22.dp)
                .requiredWidth(width = 196.dp)
                .requiredHeight(height = 23.dp)
        )
        Text(
            text = "Estar completamente presente no momento atual, com uma atitude de aceitação e sem julgamentos.",
            color = Color.Black,
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 51.dp)
                .requiredWidth(width = 180.dp)
                .requiredHeight(height = 41.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.mindfullimage),
            contentDescription = "mindfulimage",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 86.281.dp, y = 7.5.dp)
                .requiredSize(size = 109.dp)
        )
        TextButton(
            onClick = {
                try {
                    navController.navigate("mindfulness_audio")
                } catch (e: Exception) {
                    Log.e("NavigationError", "Erro ao tentar navegar: ${e.message}")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 21.dp, y = 103.dp)
                .requiredWidth(width = 88.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 100.dp)
                    .requiredHeight(height = 34.dp)
                    .background(color = Color(0xff134803))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "INICIAR",
                        color = Color.White,
                        lineHeight = 1.8.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.start),
                        contentDescription = "Iniciar",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MedGuiada(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .requiredWidth(width = 312.dp)
            .requiredHeight(height = 150.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 320.dp)
                .requiredHeight(height = 160.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xfff7f3f0))
        )
        Text(
            text = "Meditação Guiada",
            color = Color(0xff253334),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 22.dp)
                .requiredWidth(width = 196.dp)
                .requiredHeight(height = 23.dp)
        )
        Text(
            text = "Um instrutor guia a mente ao relaxamento com técnicas de visualizações e respiração.",
            color = Color.Black,
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 51.dp)
                .requiredWidth(width = 180.dp)
                .requiredHeight(height = 41.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.meditaguiada),
            contentDescription = "meditação guiada imagem",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 86.281.dp, y = 7.5.dp)
                .requiredSize(size = 109.dp)
        )
        TextButton(
            onClick = {
                try {
                    navController.navigate("meditacao_guiada")
                } catch (e: Exception) {
                    Log.e("NavigationError", "Erro ao tentar navegar: ${e.message}")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 21.dp, y = 103.dp)
                .requiredWidth(width = 88.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 100.dp)
                    .requiredHeight(height = 34.dp)
                    .background(color = Color(0xff134803))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "INICIAR",
                        color = Color.White,
                        lineHeight = 1.8.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.start),
                        contentDescription = "Iniciar",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MedSom(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .requiredWidth(width = 312.dp)
            .requiredHeight(height = 150.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 320.dp)
                .requiredHeight(height = 160.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xfff7f3f0))
        )
        Text(
            text = "Meditação do Som",
            color = Color(0xff253334),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 22.dp)
                .requiredWidth(width = 196.dp)
                .requiredHeight(height = 23.dp)
        )
        Text(
            text = "Sons e músicas promovem relaxamento e concentração, ajudando a acalmar a mente.",
            color = Color.Black,
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 51.dp)
                .requiredWidth(width = 180.dp)
                .requiredHeight(height = 41.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.medsound),
            contentDescription = "meditação som",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 86.281005859375.dp, y = 7.5.dp)
                .requiredSize(size = 109.dp)
        )
        TextButton(
            onClick = {
                try {
                    navController.navigate("meditacao_som")
                } catch (e: Exception) {
                    Log.e("NavigationError", "Erro ao tentar navegar: ${e.message}")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 21.dp, y = 103.dp)
                .requiredWidth(width = 88.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 100.dp)
                    .requiredHeight(height = 34.dp)
                    .background(color = Color(0xff134803))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "INICIAR",
                        color = Color.White,
                        lineHeight = 1.8.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.start),
                        contentDescription = "Iniciar",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun YinYoga(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .requiredWidth(width = 312.dp)
            .requiredHeight(height = 150.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 320.dp)
                .requiredHeight(height = 160.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xfff7f3f0))
        )
        Text(
            text = "Yin Yoga",
            color = Color(0xff253334),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 22.dp)
                .requiredWidth(width = 196.dp)
                .requiredHeight(height = 23.dp)
        )
        Text(
            text = "Posturas de 3-5 minutos que alongam ligamentos e promovem flexibilidade e alívio.",
            color = Color.Black,
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 51.dp)
                .requiredWidth(width = 180.dp)
                .requiredHeight(height = 41.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.yinyoga),
            contentDescription = "yingyoga",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 86.281005859375.dp, y = 7.5.dp)
                .requiredSize(size = 109.dp)
        )
        TextButton(
            onClick = {
                try {
                    navController.navigate("yin_yoga")
                } catch (e: Exception) {
                    Log.e("NavigationError", "Erro ao tentar navegar: ${e.message}")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 21.dp, y = 103.dp)
                .requiredWidth(width = 88.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 100.dp)
                    .requiredHeight(height = 34.dp)
                    .background(color = Color(0xff134803))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "INICIAR",
                        color = Color.White,
                        lineHeight = 1.8.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.start),
                        contentDescription = "Iniciar",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HathaYoga(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .requiredWidth(width = 312.dp)
            .requiredHeight(height = 150.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 320.dp)
                .requiredHeight(height = 160.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xfff7f3f0))
        )
        Text(
            text = "Hatha Yoga",
            color = Color(0xff253334),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 22.dp)
                .requiredWidth(width = 196.dp)
                .requiredHeight(height = 23.dp)
        )
        Text(
            text = "Focada em posturas e técnicas de respiração, é mais lenta e acessível para iniciantes.",
            color = Color.Black,
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 51.dp)
                .requiredWidth(width = 180.dp)
                .requiredHeight(height = 41.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.hathayogaimg),
            contentDescription = "hathayoga",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 86.281005859375.dp, y = 7.5.dp)
                .requiredSize(size = 109.dp)
        )
        TextButton(
            onClick = {
                try {
                    navController.navigate("hatha_yoga")
                } catch (e: Exception) {
                    Log.e("NavigationError", "Erro ao tentar navegar: ${e.message}")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 21.dp, y = 103.dp)
                .requiredWidth(width = 88.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 100.dp)
                    .requiredHeight(height = 34.dp)
                    .background(color = Color(0xff134803))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "INICIAR",
                        color = Color.White,
                        lineHeight = 1.8.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.start),
                        contentDescription = "Iniciar",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Home(navController = rememberNavController())
}