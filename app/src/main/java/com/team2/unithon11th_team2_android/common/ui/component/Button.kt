package com.team2.unithon11th_team2_android.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = OurTheme.color.black, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}

@Composable
fun DefaultButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = containerColor, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                modifier = Modifier
                    .width(35.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.item_object),
                tint = Color.Unspecified,
                contentDescription = null
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )
        }
    }
}

@Preview
@Composable
fun ButtonPreview() {
    DefaultButtonWithIcon(
        containerColor = Color.Black,
        contentColor = Color.White,
        text = "text"
    ) {}
}