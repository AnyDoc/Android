package com.bocdoc.anydoc.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bocdoc.anydoc.data.dto.AiResultDetectedDto
import com.bocdoc.anydoc.databinding.ItemAiResultDetecedInfoBinding

class AiResultDetectedInfoViewHolder(private val binding: ItemAiResultDetecedInfoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(detectedDto: AiResultDetectedDto) {
        with(binding) {
            tvAiResultItemDetectedInfo.text = detectedDto.detectedTitle
            tvAiResultItemDetectedDescription.text = detectedDto.detectedReason
            tvAiResultItemDetectedPercentNum.text = "${detectedDto.detectedPercent.toInt()}%"
        }
    }
}