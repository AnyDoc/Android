package com.bocdoc.anydoc.presentation

import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.databinding.FragmentSplashBinding

class SlashFragment : BindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {
    override fun initView() {
        testMoveToNextPage()
    }

    private fun testMoveToNextPage(){
        binding.tvSplashTitle.setOnClickListener {
            findNavController().navigate(R.id.action_splash_to_navigation_add_image)
        }
    }
}