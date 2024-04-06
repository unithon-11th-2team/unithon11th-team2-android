package com.team2.unithon11th_team2_android.features.map

import android.Manifest
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.team2.domain.entity.ItemType
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.bitmapDescriptorFromVector
import com.team2.unithon11th_team2_android.common.ui.component.DefaultButton
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme
import com.team2.unithon11th_team2_android.features.myitem.MY_ITEM_LIST

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION]
)
@Composable
internal fun MapScreen(
    navigateToMyItemList: () -> Unit,
    navigateToRespond: (Int) -> Unit,
    onBackPressed: () -> Unit,
    mapViewModel: MapViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val cameraPositionState = rememberCameraPositionState()


    val state by mapViewModel.uiState.collectAsState()
    var currentPinResId by remember { mutableStateOf(-1) }

    LaunchedEffect(true) {
        mapViewModel.uiEffect.collect{
            when(it){
                is MapUiEffect.Reload -> {
                    locationClient.lastLocation.addOnCompleteListener {
                        it.result.apply {
                            mapViewModel.setEvent(MapUiEvent.InitCurrentLocation(latitude, longitude))
                            mapViewModel.setEvent(MapUiEvent.FetchItemList)
                            cameraPositionState.position =
                                CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 15f)
                        }
                    }
                }
            }
        }
    }

    BackHandler {
        onBackPressed()
    }

    LaunchedEffect(state.sheetData.step) {
        mapViewModel.loadData()
        currentPinResId = when (state.sheetData.step) {
            Step.INIT -> R.drawable.my_pin
            Step.LEVEL -> R.drawable.pin_empty
            else -> {
                when (state.sheetData.level) {
                    ItemType.TYPE1 -> R.drawable.pin_mine_type1
                    ItemType.TYPE2 -> R.drawable.pin_mine_type2
                    ItemType.TYPE3 -> R.drawable.pin_mine_type3
                    ItemType.TYPE4 -> R.drawable.pin_mine_type4
                    ItemType.TYPE5 -> R.drawable.pin_mine_type5
                    else -> -1
                }
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            state.items.forEach {
                val resId = when(it.type){
                    ItemType.TYPE1 -> if(it.isMine) R.drawable.pin_mine_type1 else R.drawable.pin_other_type1
                    ItemType.TYPE2 -> if(it.isMine) R.drawable.pin_mine_type2 else R.drawable.pin_other_type2
                    ItemType.TYPE3 -> if(it.isMine) R.drawable.pin_mine_type3 else R.drawable.pin_other_type3
                    ItemType.TYPE4 -> if(it.isMine) R.drawable.pin_mine_type4 else R.drawable.pin_other_type4
                    ItemType.TYPE5 -> if(it.isMine) R.drawable.pin_mine_type5 else R.drawable.pin_other_type5
                }
                CustomPin(location = LatLng(it.latitude, it.longitude), iconResId = resId){
                    navigateToRespond(it.id ?: -1)
                }
            }.also {
                CustomPin(state.currentLocation, currentPinResId, 1.0f)
            }
        }
        MapTopBar(modifier = Modifier.align(TopCenter), navigateToMyItemList = navigateToMyItemList, onClick = onBackPressed)

        when (state.sheetData.step) {
            Step.INIT -> {
                DropItemScreen(modifier = Modifier.align(BottomCenter)) {
                    mapViewModel.setEvent(MapUiEvent.OnClickDropItem)
                }
            }

            Step.LEVEL -> {
                SelectItemScreen(modifier = Modifier.align(BottomCenter)) {
                    mapViewModel.setEvent(MapUiEvent.OnSelectLevel(it))
                }
            }

            Step.CONTENT -> {
                InputContentScreen(
                    modifier = Modifier.align(BottomCenter),
                    onTextChanged = { mapViewModel.updateContent(it) }
                ){
                    mapViewModel.setEvent(MapUiEvent.OnClickRegister)
                }
            }
        }
    }
}

@Composable
internal fun DropItemScreen(modifier: Modifier = Modifier, onClickBtn: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp)
            .background(Color.Transparent)
    ) {
        Box(
            Modifier
                .align(BottomCenter)
                .height(165.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .align(Center)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier.align(CenterHorizontally),
                    text = stringResource(id = R.string.title_text_drop_item),
                    style = MaterialTheme.typography.bodyLarge,
                    color = OurTheme.color.black
                )
                DefaultButton(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = stringResource(id = R.string.btn_text_drop_item),
                    onClick = onClickBtn
                )
            }
        }
        Icon(
            modifier = Modifier
                .align(TopCenter)
                .size(46.dp),
            painter = painterResource(id = R.drawable.item_default),
            tint = Color.Unspecified,
            contentDescription = null
        )
    }
}

@Composable
internal fun SelectItemScreen(modifier: Modifier = Modifier, onClickItem: (ItemType) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title_text_select_item),
            style = MaterialTheme.typography.bodyLarge,
            color = OurTheme.color.black
        )

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            ItemType.values().forEach {
                val resId = when (it) {
                    ItemType.TYPE1 -> R.drawable.object_type1
                    ItemType.TYPE2 -> R.drawable.object_type2
                    ItemType.TYPE3 -> R.drawable.object_type3
                    ItemType.TYPE4 -> R.drawable.object_type4
                    ItemType.TYPE5 -> R.drawable.object_type5
                }
                Icon(
                    modifier = Modifier
                        .width(46.dp)
                        .wrapContentHeight()
                        .clickable { onClickItem(it) },
                    painter = painterResource(id = resId),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
internal fun InputContentScreen(
    modifier: Modifier = Modifier,
    onTextChanged:(String) -> Unit,
    onClickBtn: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = text,
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = {
                text = it
                onTextChanged(it)
            }
        ) { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 20.dp),
                contentAlignment = TopStart
            ){
                if (text.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.hint_text_content),
                        style = MaterialTheme.typography.bodyLarge,
                        color = OurTheme.color.gray
                    )
                }
                innerTextField()
            }
        }

        DefaultButton(text = stringResource(id = R.string.btn_text_register), onClick = onClickBtn)
    }
}

@Composable
internal fun MapTopBar(modifier: Modifier = Modifier, onClick: () -> Unit, navigateToMyItemList: () -> Unit) {
    Box(
        modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Transparent)
            .padding(end = 20.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(48.dp)
                .align(CenterStart),
            onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier.align(CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionButton(title = stringResource(id = R.string.btn_text_fetch_items), navigateToMyItemList)
            // TODO navigateToRanking
            ActionButton(title = stringResource(id = R.string.btn_text_ranking), navigateToMyItemList)
        }
    }
}

@Composable
internal fun ActionButton(title: String, moveToPage: ()-> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.White, shape = RoundedCornerShape(15.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable {
                moveToPage()
            }
    ) {
        Row(modifier = Modifier.align(Center)) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(CenterVertically),
                painter = painterResource(id = R.drawable.item_object),
                tint = Color.Unspecified,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                modifier = Modifier.align(CenterVertically),
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = OurTheme.color.black
            )
        }
    }
}

@Composable
internal fun CustomPin(location: LatLng, iconResId: Int, zIndex: Float = 0.0f, onClick: () -> Unit = {}) {
    val context = LocalContext.current
    val icon = bitmapDescriptorFromVector(
        context, iconResId
    )
    Marker(state = MarkerState(location), icon = icon, zIndex = zIndex, onClick = {
        onClick()
        false
    })
}

@Preview
@Composable
fun CommonPreview() {

}