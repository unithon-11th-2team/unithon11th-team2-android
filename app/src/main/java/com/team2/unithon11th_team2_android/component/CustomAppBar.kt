package com.team2.unithon11th_team2_android.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.theme.OurColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithBackNavigation(
    modifier: Modifier = Modifier,
    isBackIconVisible: Boolean = true,
    appbarColor: Color,
    onBackButtonAction: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            if (isBackIconVisible) {
                IconButton(onClick = {
                    onBackButtonAction.invoke()
                }) {
                    BackIcon()
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = appbarColor,
            navigationIconContentColor = OurColorPalette.current.black
        )
    )
}

@Composable
fun BackIcon() {
    Icon(painter = painterResource(id = R.drawable.ic_left_arrow), contentDescription = null)
}