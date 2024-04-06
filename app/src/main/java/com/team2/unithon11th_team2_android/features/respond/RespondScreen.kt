package com.team2.unithon11th_team2_android.features.respond

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.component.DefaultButtonWithIcon
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme

@Composable
fun RespondScreen() {
    Scaffold(
        containerColor = OurTheme.color.gray,
        topBar = { RespondTopBar() }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            ItemDetailContent()
            CommentContent()
            InputCommentContent()
        }
    }
}

@Composable
internal fun RespondTopBar() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(OurTheme.color.gray)
            .padding(end = 20.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterStart),
            onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "title",
            color = OurTheme.color.secondary,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
internal fun ItemDetailContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(OurTheme.color.gray)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Icon(
                modifier = Modifier
                    .width(90.dp)
                    .wrapContentHeight(),
                painter = painterResource(id = R.drawable.object_type3),
                tint = Color.Unspecified,
                contentDescription = null
            )
            Column(modifier = Modifier.align(Alignment.Bottom)) {
                Text(
                    text = "24.04.06 AM02:45",
                    style = MaterialTheme.typography.bodyLarge,
                    color = OurTheme.color.black
                )
                Text(
                    text = "서울시 용산구",
                    style = MaterialTheme.typography.bodyLarge,
                    color = OurTheme.color.black
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            Text(text = "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ")
        }

        DefaultButtonWithIcon(
            text = "text",
            containerColor = OurTheme.color.black,
            contentColor = Color.White
        ) {

        }
    }
}

@Composable
internal fun CommentContent() {
    Column {

    }
}

@Preview
@Composable
internal fun CommentItem(){
    Column(modifier = Modifier.fillMaxWidth()){

    }
}

@Composable
internal fun InputCommentContent() {

}