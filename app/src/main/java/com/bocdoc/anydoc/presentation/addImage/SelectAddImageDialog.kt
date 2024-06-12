package com.bocdoc.anydoc.presentation.addImage

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.Window
import com.bocdoc.anydoc.databinding.SelectAddImageDialogBinding

class SelectAddImageDialog(
    context: Context,
    val clickCamera: View.OnClickListener,
    val clickGallery: View.OnClickListener
) : Dialog(context) {
    private val binding: SelectAddImageDialogBinding =
        SelectAddImageDialogBinding.inflate(layoutInflater)

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀바 제거
        setContentView(binding.root)
        window?.apply {
            setGravity(Gravity.CENTER) // 다이얼로그 위치
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 테두리 없애기
        }
        initCameraBtnClickListener()
        initGalleryBtnClickListener()
    }

    // 카메라 버튼
    private fun initCameraBtnClickListener() {
        with(binding) {
            btnAddImageCamera.setOnClickListener {
                btnAddImageCamera.isSelected = true
                btnAddImageGallery.isSelected = false
                clickCamera.onClick(it)
                dismiss()
            }
        }
    }

    // 갤러리 버튼
    private fun initGalleryBtnClickListener() {
        with(binding) {
            btnAddImageGallery.setOnClickListener {
                btnAddImageGallery.isSelected = true
                btnAddImageCamera.isSelected = false
                clickGallery.onClick(it)
                dismiss()
            }
        }
    }
}