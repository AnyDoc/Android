package com.bocdoc.anydoc.presentation

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.fragment.colorOf
import com.bocdoc.anydoc.databinding.FragmentSplashBinding

class SplashFragment : BindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeStatusBarColor()
        moveToNextPage()
    }

    private fun changeStatusBarColor() {
        activity?.window?.apply {
            statusBarColor = colorOf(R.color.anydoc_primary_10)
        }
    }

    private fun moveToNextPage() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splash_to_navigation_onboarding)
            activity?.window?.apply {
                statusBarColor = colorOf(R.color.anydoc_white)
            }
        }, 1500)
    }
}