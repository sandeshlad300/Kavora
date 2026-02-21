package com.tourism.feature_splash


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage

@Composable
fun SplashScreen(progress: Float) {





    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background Image
        AsyncImage(
            model = R.drawable.ic_splash,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            // Logo
            AsyncImage(
                model = R.drawable.ic_compass,
                modifier = Modifier.size(40.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "KAVORA",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 6.sp,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "TRAVEL QUIETLY.",
                style = TextStyle(
                    fontSize = 12.sp,
                    letterSpacing = 3.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Section
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "PREPARING YOUR ESCAPE",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 10.sp,
                        letterSpacing = 2.sp
                    )

                    Text(
                        text = "${(progress * 100).toInt()}%",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = Color(0xFF7C4DFF),
                    trackColor = Color.White.copy(alpha = 0.2f),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "📍 KONKAN COAST",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 11.sp
                )
            }
        }
    }
}