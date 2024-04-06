package com.team2.unithon11th_team2_android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex

@Composable
fun Toast(modifier: Modifier = Modifier, message: String, isVisible: Boolean = false) {
    if (!isVisible) return

    Dialog(
        onDismissRequest = {}, properties = DialogProperties()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(bottom = 100.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                modifier = modifier
                    .zIndex(1f)
                    .fillMaxWidth()
                    .background(
                        color = Color.Black
                    )
                    .padding(10.dp),
                text = message,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun ToastPreview() {
    Toast(message = "Toast Test!", isVisible = true)
}