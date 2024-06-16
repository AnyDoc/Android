package com.bocdoc.anydoc.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.fragment.colorOf
import com.bocdoc.anydoc.coreui.fragment.statusBarColorOf
import com.bocdoc.anydoc.databinding.FragmentImageBinding
import com.bocdoc.anydoc.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImgFragment : BindingFragment<FragmentImageBinding>(R.layout.fragment_image) {


    override fun initView() {
        loadImg()
        clickBackButton()
    }

    private fun loadImg() {
        arguments?.let {
            val imgUrl = it.getString("imgUrl")
            binding.ivAiResultImg.load(imgUrl) {
                crossfade(true)
                placeholder(R.drawable.shape_primary_99_fill_16_rect)
            }
        }
    }

    private fun clickBackButton() {
        binding.ivAiResultImgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}