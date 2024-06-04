package com.bocdoc.anydoc.presentation.addImage

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore

class CameraGalleryManager() {
    fun getCameraIntent(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    }

    fun getGalleryIntent(): Intent {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        return galleryIntent
    }

    fun getSelectedImageUri(data: Intent?): Uri? {
        return data?.data
    }
}