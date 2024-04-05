package com.team2.unithon11th_team2_android.features.permission

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    onGrantPermission: () -> Unit
) {
    val locationPermission =
        rememberPermissionState(permission = Manifest.permission.ACCESS_COARSE_LOCATION)

    LaunchedEffect(locationPermission.status) {
        if (locationPermission.status.isGranted) {
            onGrantPermission()
        } else {
            if (locationPermission.status.shouldShowRationale) {
                // has denied
            } else {
                // first time
            }
        }

    }
}