package com.bocdoc.anydoc.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.databinding.ItemAiResultImgBinding

class AiResultImgViewHolder(private val binding: ItemAiResultImgBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imgUri: String) {
        binding.ivAiResultItemImg.load(imgUri) {
            crossfade(true)
            placeholder(R.drawable.shape_primary_99_fill_16_rect)
            transformations(RoundedCornersTransformation(16.0f))
        }
    }
}