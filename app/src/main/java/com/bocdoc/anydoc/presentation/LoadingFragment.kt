package com.bocdoc.anydoc.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.databinding.FragmentLoadingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingFragment : BindingFragment<FragmentLoadingBinding>(R.layout.fragment_loading) {

    private var job: Job? = null
    private var currentIndex = 0
    private val textResources = listOf(
        R.string.loading_info_text01,
        R.string.loading_info_text02,
        R.string.loading_info_text03
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveToNextPage()
        updateText()
    }

    private fun updateText() {
        job = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(2000)
                changeInfoText()
            }
        }
    }

    private fun changeInfoText() {
        val nextTextResource = textResources[currentIndex]
        binding.tvLoadingInfo.text = resources.getString(nextTextResource)

        currentIndex = (currentIndex + 1) % textResources.size
    }

    private fun moveToNextPage() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(6000)
            findNavController().navigate(R.id.action_loading_to_navigation_ai_result)
        }
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }
}