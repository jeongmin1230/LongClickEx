package com.example.longclickex

import android.app.AlertDialog
import android.os.Bundle
import android.os.SystemClock.uptimeMillis
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.longclickex.ui.theme.LongClickExTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LongClickExTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.wrapContentSize()) {

                        ShowDialog()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Red)
            .pointerInput(Unit) {
                var startTime = 0L
                var isLongClick = false
                detectTapGestures(
                    onLongPress = {
                        isLongClick = true
                        // LongClick 이벤트가 발생했을 때 처리할 로직을 작성합니다.
                        println("long click")
                    },
                    onPress = {
                        startTime = uptimeMillis()
                        isLongClick = false
                        println("short click :: $startTime")
                    },
                )
            }
    ) {
        // Box 내부에 표시할 컨텐츠를 작성합니다.
        Text(text = "여기 눌러보세용")
    }
}

@Composable
fun ShowDialog(){
    val context = LocalContext.current
    var value by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .background(Color.Cyan)
        .wrapContentSize()
        .pointerInput(Unit) {
            var startTime = 0L
            var isLongClick = false
            detectTapGestures(
                onLongPress = {
                    isLongClick = true
                    // LongClick 이벤트가 발생했을 때 처리할 로직을 작성합니다.
                    println("long click")
                    value = !value
                },
                onPress = {
                    startTime = uptimeMillis()
                    isLongClick = false
                    println("short click :: $startTime")
                },
            )
        }
    ) {
        // Box 내부에 표시할 컨텐츠를 작성합니다.
        MyAlertDialog(value = value)
        Text(text = "click")
    }

}

@Composable
fun MyAlertDialog(value: Boolean) {
    if(value) {
        AlertDialog(
            onDismissRequest = { /* handle dialog dismiss */ },
            title = { Text(text = "글 제목") },
            text = { Text("수정하실?") },
            confirmButton = {
                TextButton(onClick = { println("수정할게용") }) {
                    Text(text = "이 글 수정?")
                }
            },
            dismissButton = { Text(text = "닫기") },
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LongClickExTheme {
        Greeting("Android")
    }
}