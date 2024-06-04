package com.bocdoc.anydoc.presentation.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bocdoc.anydoc.data.dto.AiResultDetectedDto
import com.bocdoc.anydoc.databinding.ItemAiResultDetecedInfoBinding

class AiResultDetectedInfoViewHolder(private val binding: ItemAiResultDetecedInfoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(detectedDto: AiResultDetectedDto) {
        binding.tvAiResultItemDetectedInfo.text = detectedDto.detectedTitle
        binding.tvAiResultItemDetectedDescription.text = detectedDto.detectedReason
        binding.tvAiResultItemDetectedPercentNum.text = "${detectedDto.detectedPercent.toInt()}%"
    }
}