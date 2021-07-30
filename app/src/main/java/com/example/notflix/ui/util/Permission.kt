package com.example.notflix.ui.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat

fun cameraPermission(context: Context, permissionType : String, activity : Activity, permissionCode : Int){
    if (ActivityCompat.checkSelfPermission(context,permissionType) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(activity, arrayOf(permissionType),permissionCode)
    } else Toast.makeText(context,"Permission already granted",Toast.LENGTH_SHORT).show()
}