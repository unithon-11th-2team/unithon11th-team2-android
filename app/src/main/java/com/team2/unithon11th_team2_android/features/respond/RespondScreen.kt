package com.team2.unithon11th_team2_android.features.respond

import android.media.MediaPlayer
import androidx.compose.foundation.Image
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.team2.domain.entity.Comment
import com.team2.domain.entity.ItemType
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.component.DefaultButtonWithIcon
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme

@Composable
internal fun RespondScreen(
    respondViewModel: RespondViewModel = hiltViewModel()
) {
    val state by respondViewModel.uiState.collectAsState()
    var isPlay by remember { mutableStateOf(false) }

    LaunchedEffect(state.clicked) {
        if (state.clicked) {
            isPlay = true
        }
    }
    Scaffold(
        containerColor = OurTheme.color.gray,
        topBar = { RespondTopBar(state.userName) }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            ItemDetailContent(
                type = state.type,
                date = state.date,
                address = state.address,
                message = state.message,
                count = state.count,
                clicked = state.clicked,
                onClickItem = {
                    respondViewModel.setEvent(RespondUiEvent.OnClickItem)
                }
            )
            CommentContent(
                listOf(
                    Comment(0, "바보", "PEACEFUL......"),
                    Comment(0, "메롱", "ㅠㅠㅠㅠㅠ 안녕하세요"),
                    Comment(0, "애옹", "ㅎㅎㅎㅎㅎㅎㅎ")
                )
            )
//            InputCommentContent()
        }
        if (isPlay) {
            AnimateItemScreen { isPlay = !isPlay }
        }
    }
}

@Composable
internal fun RespondTopBar(nickname: String) {
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
            text = stringResource(id = R.string.appbar_title_detail, nickname),
            color = OurTheme.color.secondary,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
internal fun ItemDetailContent(
    type: ItemType,
    date: String,
    address: String,
    message: String,
    count: Int,
    clicked: Boolean,
    onClickItem: () -> Unit
) {
    val resId = when (type) {
        ItemType.TYPE1 -> R.drawable.object_type1
        ItemType.TYPE2 -> R.drawable.object_type2
        ItemType.TYPE3 -> R.drawable.object_type3
        ItemType.TYPE4 -> R.drawable.object_type4
        ItemType.TYPE5 -> R.drawable.object_type5
    }
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
                painter = painterResource(id = resId),
                tint = Color.Unspecified,
                contentDescription = null
            )
            Column(modifier = Modifier.align(Alignment.Bottom)) {
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyLarge,
                    color = OurTheme.color.black
                )
                Text(
                    text = address,
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
            Text(text = message)
        }

        DefaultButtonWithIcon(
            text = count.toString(),
            containerColor = if (clicked) OurTheme.color.primary else OurTheme.color.black,
            contentColor = if (clicked) Color.Black else Color.White
        ) { onClickItem() }
    }
}

@Composable
internal fun CommentContent(
    comments: List<Comment>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title_comment) + " ${comments.size}",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = OurTheme.color.black
        )
        comments.forEach {
            CommentItem(nickname = it.nickname, message = it.message)
        }
    }
}

@Composable
internal fun CommentItem(
    nickname: String,
    message: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = nickname, color = Color(0xff9D9D9D))
        Text(text = message)
    }
    HorizontalDivider()
}

@Composable
internal fun InputCommentContent() {

}

@Composable
fun AnimateItemScreen(onFinish: () -> Unit) {
    val context = LocalContext.current
    val mediaPlayer = MediaPlayer.create(context, R.raw.item_play)
    LaunchedEffect(true) {
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { onFinish() }
    }
    GifImage()
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components { add(ImageDecoderDecoder.Factory()) }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.animate_item).apply(block = {
                size(1000)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
fun CommonPreview() {
    CommentContent(comments = listOf(Comment(0, "nickname", "message")))
}