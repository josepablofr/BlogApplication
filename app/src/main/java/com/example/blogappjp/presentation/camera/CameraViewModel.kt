package com.example.blogappjp.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.blogappjp.core.Result
import com.example.blogappjp.domain.camera.CameraRepo

class CameraViewModel(private val repo: CameraRepo): ViewModel() {

    fun uploadPhoto(imageBitmap: Bitmap, description: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.uploadPhoto(imageBitmap, description)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}

class CameraViewModelFactory(private val repo: CameraRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CameraRepo::class.java).newInstance(repo)
    }
}