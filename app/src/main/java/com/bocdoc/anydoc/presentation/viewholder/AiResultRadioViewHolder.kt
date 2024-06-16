package com.bocdoc.anydoc.presentation.viewholder

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.databinding.ItemAiResultImgBinding
import com.bocdoc.anydoc.databinding.ItemAiResultRadioBinding

class AiResultRadioViewHolder(private val binding: ItemAiResultRadioBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(radioTitle: String) {
        binding.tvAiResultRadioTitle.text = radioTitle
    }
}