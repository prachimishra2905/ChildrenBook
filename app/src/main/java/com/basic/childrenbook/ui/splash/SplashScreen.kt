package com.basic.childrenbook.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }

    val scaleAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scaleAnim"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "splashInfinite")
    val flutterAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 35f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "flutterAnim"
    )

    val pulseAnim by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulseAnim"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2600)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF4F46E5), Color(0xFF4338CA), Color(0xFF581C87))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Pulsing background ring
        Box(
            modifier = Modifier
                .size(260.dp)
                .scale(pulseAnim)
                .clip(CircleShape)
                .background(Color(0xFFFDE68A).copy(alpha = 0.15f))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.scale(scaleAnim.value)
        ) {
            // Mascot & Fluttering Book Icon Box
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color(0xFFF59E0B)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(10.dp)
                            .height(50.dp)
                            .background(Color(0xFF312E81), RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
                    )
                    Box(
                        modifier = Modifier
                            .width(36.dp)
                            .height(50.dp)
                            .graphicsLayer {
                                rotationY = flutterAnim
                                cameraDistance = 8f * density
                            }
                            .background(Color.White, RoundedCornerShape(topEnd = 6.dp, bottomEnd = 6.dp))
                    )
                }
                Text("🐣", fontSize = 36.sp, modifier = Modifier.align(Alignment.Center))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Baby Nook 🐣",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "STORIES FOR CURIOUS LITTLE MINDS",
                color = Color(0xFFFDE68A),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
        }
    }
}
