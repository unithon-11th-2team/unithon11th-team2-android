package com.team2.unithon11th_team2_android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team2.unithon11th_team2_android.common.ui.theme.OurColorPalette
import com.team2.unithon11th_team2_android.common.ui.theme.OurTypo
import com.team2.unithon11th_team2_android.common.ui.util.dpToPx

@Composable
fun PlainInputTextField(
    modifier: Modifier = Modifier,
    innerTextModifier: Modifier = Modifier,
    text: TextFieldValue,
    hint: String,
    onClickAction: () -> Unit,
    onTextChanged: (TextFieldValue) -> Unit
) {
    val colorSet = OurColorPalette.current
    var color = remember { mutableStateOf(colorSet.black) }
    BasicTextField(modifier = modifier.onFocusChanged {
        color.value = if (it.isFocused) colorSet.red else colorSet.black
    },
        value = text,
        onValueChange = onTextChanged,
        textStyle = OurTypo.current.Body01,
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { onClickAction() }),
        decorationBox = { innerTextField ->
            InputTextFieldBox(
                modifier = innerTextModifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(8.dp), color = color.value, borderSize = 1.dp.dpToPx()
            ) {
                if (text.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = OurColorPalette.current.black,
                        style = OurTypo.current.Body01,
                    )
                }
                innerTextField()
            }
        })
}

@Composable
fun InputTextFieldBox(
    modifier: Modifier = Modifier,
    color: Color,
    borderSize: Float,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.drawBehind {
        drawLine(
            color = color,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = borderSize
        )
    }) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            content()
        }
    }
}

@Composable
fun TitleText(title: String, modifier: Modifier, textAlign: TextAlign = TextAlign.Left) {
    Text(
        text = title,
        style = OurTypo.current.Heading,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun MainButton(title: String, fontSize: Int = 10, modifier: Modifier, onClickAction: () -> Unit) {
    Button(
        onClick = {
            onClickAction()

        },
        modifier = modifier,
        shape = RoundedCornerShape(size = fontSize.dp),
        colors = ButtonDefaults.run {
            buttonColors(
                disabledContainerColor = OurColorPalette.current.brown,
                disabledContentColor = OurColorPalette.current.white,
                containerColor = OurColorPalette.current.brown,
                contentColor = OurColorPalette.current.white,
            )
        },
        ) {
        Text(text = title)
    }
}

@Preview
@Composable
fun previewInput() {
    Column {
        TitleText(title = "Texst", modifier = Modifier.background(Color.White))
        MainButton(title = "버튼이닷", modifier = Modifier, onClickAction = {

        })
    }

}