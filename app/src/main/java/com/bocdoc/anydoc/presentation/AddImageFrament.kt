package com.bocdoc.anydoc.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.databinding.FragmentAddImageBinding

class AddImageFrament : BindingFragment<FragmentAddImageBinding>(R.layout.fragment_add_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveToNextPage()
    }

    private fun moveToNextPage() {
        findNavController().navigate(R.id.action_add_image_to_navigation_loading)
    }
}