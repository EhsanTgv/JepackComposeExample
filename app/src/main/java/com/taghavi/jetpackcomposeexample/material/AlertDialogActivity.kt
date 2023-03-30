package com.taghavi.jetpackcomposeexample.material

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AlertDialogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ClickableText()
            }
        }
    }
}

@Composable
fun ClickableText() {
    var showPopup by remember { mutableStateOf(false) }

    Column(Modifier.clickable(onClick = { showPopup = true }), content = {
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray,
            ),
        ) {
            Text(
                text = "Click to see dialog",
                modifier = Modifier.padding(16.dp),
                style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.Serif)
            )
        }
    })

    val onPopupDismissed = { showPopup = false }

    if (showPopup) {
        AlertDialog(onDismissRequest = onPopupDismissed,
            text = {
                Text("Congratulation! You just clicked the text successfully")
            },
            confirmButton = {
                Button(onClick = onPopupDismissed) {
                    Text(text = "OK")
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun ClickableTextPreview() {
    Column {
        ClickableText()
    }
}