package com.bocdoc.anydoc.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.fragment.colorOf
import com.bocdoc.anydoc.coreui.fragment.statusBarColorOf
import com.bocdoc.anydoc.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private var job: Job? = null

    override fun initView() {
        statusBarColorOf(R.color.anydoc_primary_10)
        moveToNextPage()
    }

    private fun moveToNextPage() {
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            findNavController().navigate(R.id.action_splash_to_navigation_onboarding)
            activity?.window?.apply {
                statusBarColor = colorOf(R.color.anydoc_white)
            }
        }
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }
}