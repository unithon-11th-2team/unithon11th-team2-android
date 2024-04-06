package com.team2.unithon11th_team2_android.features.home

import android.Manifest
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION]
)
@Composable
fun HomeScreen(
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(true) {
        locationClient.lastLocation.addOnCompleteListener {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(it.result.latitude, it.result.longitude), 15f)
        }
    }

    BackHandler {
        onBackPressed()
    }

    Scaffold {
        Box(Modifier.padding(it)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            )
        }
    }
}