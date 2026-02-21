package com.tourism.feature_intro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun IntroScreen(
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
    currentPage: Int = 0
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0E1C36),
                        Color(0xFF081022)
                    )
                )
            )
            .padding(16.dp)
    ) {

        // Skip Button
        Text(
            text = "Skip",
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .clickable { onSkipClick() }
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0C1830)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                // Image


                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Discover Hidden Gems",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Escape the crowds and explore the untouched beauty of the Konkan coastline. Your journey to the secret shores starts here.",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                // Dots Indicator
                DotsIndicator(
                    totalDots = 3,
                    selectedIndex = currentPage
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2D6AE3)
                    )
                ) {
                    Text("Next  →")
                }
            }
        }
    }
}
