package com.bocdoc.anydoc.presentation

import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.fragment.statusBarColorOf
import com.bocdoc.anydoc.databinding.FragmentOnboardingBinding

class OnboardingFragment :
    BindingFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

    override fun initView() {
        statusBarColorOf(R.color.anydoc_white)
        setOnboardingTitle()
        clickNextButton()
    }

    private fun setOnboardingTitle() {
        val styledText = getString(R.string.onboarding_title)
        binding.tvOnboardingTitle.text =
            HtmlCompat.fromHtml(styledText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun clickNextButton() {
        binding.tvOnboardingButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_to_navigation_add_image)
        }
    }
}