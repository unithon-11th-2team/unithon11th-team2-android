package com.team2.unithon11th_team2_android.features.permission

import android.Manifest
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    onGrantPermission: () -> Unit
) {
    Timber.e("##### PERMISSION SCREEN")
    val locationPermission =
        rememberPermissionState(permission = Manifest.permission.ACCESS_COARSE_LOCATION)

    LaunchedEffect(locationPermission.status) {
        if (locationPermission.status.isGranted) {
            Log.e("gowoon", " granted")
            onGrantPermission()
        } else {
            if (locationPermission.status.shouldShowRationale) {
                Log.e("gowoon", "rationale")
            } else {
                Log.e("gowoon", "first")
            }
            locationPermission.launchPermissionRequest()
        }

    }
}