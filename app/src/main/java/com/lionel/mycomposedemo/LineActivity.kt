package com.lionel.mycomposedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.lionel.mycomposedemo.page.LinePage
import com.lionel.mycomposedemo.ui.theme.JetchatTheme
import com.lionel.mycomposedemo.ui.theme.MyComposeDemoTheme
import java.time.format.TextStyle

class LineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LinePage(modifier = Modifier.fillMaxSize())
                        Text(
                            text = "hahaha",
                            style = androidx.compose.ui.text.TextStyle(color = Color.White)
                        )
                    }
                }
            }
        }
    }


}