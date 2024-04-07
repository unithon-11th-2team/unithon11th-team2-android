package com.team2.unithon11th_team2_android.features.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme

enum class Mode(val list: List<String>) {
    Positive(
        listOf(
            "서울특별시 노원구 공릉동",
            "서울특별시 성북구 상월곡동",
            "서울특별시 성동구 송정동",
            "서울특별시 광진구 화양동",
            "서울특별시 성동구 성수동2가",
            "서울특별시 영등포구 여의도동",
            "서울특별시 강남구 역삼동",
            "인천광역시 강화군 강화읍 국화리",
            "서울특별시 강남구 대치동",
            "서울특별시 서초구 서초동"
        )
    ),
    Negative(
        listOf(
            "서울특별시 광진구 화양동",
            "경기도 남양주시 와부읍 도곡리",
            "서울특별시 중랑구 신내동",
            "서울특별시 광진구 군자동",
            "대전광역시 서구 탄방동",
            "경기도 포천시 화현면 화현리",
            "북한",
            "서울특별시 성동구 성수동2가",
            "서울특별시 종로구 행촌동",
            "서울특별시 영등포구 여의도동",

            )
    )
}

@Composable
fun RankingScreen() {
    var mode by remember { mutableStateOf(Mode.Positive) }
    Scaffold(
        containerColor = OurTheme.color.gray,
        topBar = { RankingTopBar() }) {
        Box(Modifier.padding(it)) {
            RankingContent(
                mode = mode,
                onClickPositive = { mode = Mode.Positive },
                onClickNegative = { mode = Mode.Negative }
            )
        }
    }
}

@Composable
internal fun RankingTopBar() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(OurTheme.color.gray)

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
    }
}

@Composable
fun RankingContent(
    mode: Mode,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            ToggleButton(text = "극락", enable = mode == Mode.Positive) { onClickPositive() }
            ToggleButton(text = "나락", enable = mode == Mode.Negative) { onClickNegative() }
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            mode.list.forEachIndexed { index, s ->
                RankingItem(rank = index, address = s)
            }
        }
    }
}

@Composable
fun ToggleButton(
    text: String,
    enable: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(42.dp)
            .background(
                color = if (enable) Color(0xffD5A458) else Color(0xffF5F4F1),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            color = if (enable) Color.Black else Color(0xffC7C7C7),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun RankingItem(
    rank: Int,
    address: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .background(color = Color.White, shape = RoundedCornerShape(13.dp))
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "${rank + 1} 위",
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(text = address, color = Color.Black, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
fun TestPreveiw() {
    RankingScreen()
}