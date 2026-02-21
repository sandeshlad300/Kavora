package com.tourism.feature_intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == selectedIndex) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == selectedIndex)
                            Color(0xFF2D6AE3)
                        else
                            Color.White.copy(alpha = 0.3f)
                    )
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}