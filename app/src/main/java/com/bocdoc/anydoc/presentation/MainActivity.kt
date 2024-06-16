package com.bocdoc.anydoc.presentation

import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingActivity
import com.bocdoc.anydoc.databinding.ActivityMainBinding

class MainActivity  : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
      supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
    }

}