package com.example.animation1


import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation1.ui.theme.Animation1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sizeState by remember{
                mutableStateOf(200.dp)
            }
            val size by animateDpAsState(
                targetValue = sizeState,
                tween(
                    durationMillis = 3000,
                    delayMillis = 0,
                    easing = FastOutSlowInEasing
                )
//            spring(
//                dampingRatio = Spring.DampingRatioHighBouncy
//            ) // springy animation        //both of these are animation spec we can custom animation spec with the help of keyframes
            )

            val infiniteTransition = rememberInfiniteTransition()
            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Cyan,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 5000),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Box(modifier = Modifier
                .size(size)
                .background(color),
                contentAlignment = Alignment.Center,
            ){
                Button(onClick = { sizeState= sizeState + 50.dp }) {
                    Text("Click to increase size")
                }
            }


        }
    }
}



