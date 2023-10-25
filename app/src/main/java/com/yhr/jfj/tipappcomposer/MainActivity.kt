package com.yhr.jfj.tipappcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yhr.jfj.tipappcomposer.ui.theme.TipAppComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Text(text = "YHRJFJ")
            }
        }
    }
}

// Main Function
@Composable
fun MyApp(content: @Composable () -> Unit) {
    TipAppComposerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

// TopHeader Functiion where user can see how much money the need to pay
@Preview
@Composable
fun TopHeader() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp)))
//            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
    ) {
        Column {

        }
    }
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipAppComposerTheme {
        MyApp {
            Text(text = "YHRJFJ")
        }
    }
}