package com.team2.unithon11th_team2_android.features.myitem

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.team2.domain.entity.ItemInfo
import com.team2.domain.entity.ItemType
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.theme.OurColorPalette
import com.team2.unithon11th_team2_android.common.ui.theme.OurTypo
import com.team2.unithon11th_team2_android.component.AppBarWithBackNavigation
import com.team2.unithon11th_team2_android.component.TitleText

@Composable
internal fun MyItemListScreen(
    onBackPressed: () -> Unit,
    onMovePage: () -> Unit,
    myItemListViewModel: MyItemListViewModel = hiltViewModel()
) {
    myItemListViewModel.setEvent(MyItemListUiEvent.GetMyItemList)

    BackHandler {
        onBackPressed()
    }


    val state = myItemListViewModel.uiState.collectAsState().value
    Column {
        AppBarWithBackNavigation(modifier = Modifier.fillMaxWidth(),
            isBackIconVisible = true,
            appbarColor = OurColorPalette.current.white,
            onBackButtonAction = {
                // TODO Back - 앱 종료!
            })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            TitleText(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.my_item_list_title)
            )

            if (state.items.isEmpty()) {
                EmptyMyItemView(modifier = Modifier) {
                    onBackPressed()
                }
            } else {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(state.items.size) { idx ->
                        val item = state.items[idx]
                        MyItem(item, myItemListViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyMyItemView(modifier: Modifier, onBackPressed: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.empty_item_title),
            textAlign = TextAlign.Center,
            style = OurTypo.current.Heading,
        )
        Image(
            painter = painterResource(id = R.drawable.group_135),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Image (painter = painterResource(id = R.drawable.hanging_item_btn),
            contentDescription = null, modifier = Modifier.clickable {
                onBackPressed()
            })
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
internal fun MyItem(item: ItemInfo, viewModel: MyItemListViewModel) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .border(
                width = 1.dp,
                color = OurColorPalette.current.rightGold,
                shape = RoundedCornerShape(10.dp)
            )
            .background(Color.White)
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {

        Row {
            Row() {
                val icons: Int
                when (item.type) {
                    ItemType.TYPE1 -> {
                        icons = R.drawable.group_106
                    }

                    ItemType.TYPE2 -> {
                        icons = R.drawable.group_105
                    }

                    ItemType.TYPE3 -> {
                        icons = R.drawable.group_104
                    }

                    ItemType.TYPE4 -> {
                        icons = R.drawable.group_103
                    }

                    ItemType.TYPE5 -> {
                        icons = R.drawable.group_102
                    }
                }
                Image(
                    painter = painterResource(id = icons),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.group_134),
                contentDescription = null,
                modifier = Modifier.clickable {
                    viewModel.setEvent(MyItemListUiEvent.DeleteItem(item))
                })
        }
        Text(text = item.message)
        Row {
            Text(text = item.address)
            Text(text = "24.04.07")
        }
    }
}

