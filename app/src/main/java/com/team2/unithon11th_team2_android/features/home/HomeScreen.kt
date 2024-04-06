package com.team2.unithon11th_team2_android.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.team2.unithon11th_team2_android.common.ui.theme.OurColorScheme
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme

@Composable
fun HomeScreen(){
    Scaffold {
        Box(Modifier.padding(it)){
            Text(modifier = Modifier.align(Alignment.Center), text = "home", color = OurTheme.color.black)
        }
    }
}