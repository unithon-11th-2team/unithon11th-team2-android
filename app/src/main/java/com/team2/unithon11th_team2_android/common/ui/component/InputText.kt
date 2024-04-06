package com.team2.unithon11th_team2_android.common.ui.component

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun InputText(onValueChanged: (String) -> Unit){
    var text by remember { mutableStateOf("") }
    BasicTextField(value = text, onValueChange = onValueChanged)
}