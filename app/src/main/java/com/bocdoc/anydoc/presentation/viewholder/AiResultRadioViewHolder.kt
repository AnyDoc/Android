package com.bocdoc.anydoc.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bocdoc.anydoc.data.dto.AiResultRadioDto
import com.bocdoc.anydoc.databinding.ItemAiResultRadioBinding

class AiResultRadioViewHolder(private val binding: ItemAiResultRadioBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(aiRadio : AiResultRadioDto) {
        binding.tvAiResultRadioTitle.text = aiRadio.title
        binding.tvAiResultRadioTitle.isSelected = aiRadio.check
    }
}