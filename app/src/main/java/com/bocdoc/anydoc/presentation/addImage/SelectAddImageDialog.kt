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
    private lateinit var binding: SelectAddImageDialogBinding

    fun showDialog() {
        // Dialog 인스턴스 초기화
        binding = SelectAddImageDialogBinding.inflate(layoutInflater)

        // 콘텐츠 추가 전에 requestFeature() 호출
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀바 제거

        // Dialog 설정
        setContentView(binding.root)
        window?.setGravity(Gravity.CENTER) // 다이얼로그 위치
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 테두리 없애기
        setCanceledOnTouchOutside(true) // 다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히게
        show()

        with(binding) {
            btnSelectAddCamera.setOnClickListener(clickCamera)
            btnSelectAddGallery.setOnClickListener(clickGallery)
        }
    }
}