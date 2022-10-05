package com.example.blogapp.domain.camera

import android.graphics.Bitmap
import com.example.blogappjp.data.remote.camera.CameraDataSource
import com.example.blogappjp.domain.camera.CameraRepo

class CameraRepoImpl(private val dataSource: CameraDataSource): CameraRepo {
    override suspend fun uploadPhoto(imageBitmap: Bitmap, description: String) {
        dataSource.uploadPhoto(imageBitmap, description)
    }
}