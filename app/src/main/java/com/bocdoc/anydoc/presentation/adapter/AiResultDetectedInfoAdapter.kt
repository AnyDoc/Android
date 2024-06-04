package com.bocdoc.anydoc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bocdoc.anydoc.data.dto.AiResultDetectedDto
import com.bocdoc.anydoc.databinding.ItemAiResultDetecedInfoBinding
import com.bocdoc.anydoc.presentation.viewholder.AiResultDetectedInfoViewHolder

class AiResultDetectedInfoAdapter :
    ListAdapter<AiResultDetectedDto, AiResultDetectedInfoViewHolder>(
        AiResultDetectedInfoDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AiResultDetectedInfoViewHolder {
        val binding = ItemAiResultDetecedInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AiResultDetectedInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AiResultDetectedInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AiResultDetectedInfoDiffCallback : DiffUtil.ItemCallback<AiResultDetectedDto>() {
    override fun areItemsTheSame(
        oldItem: AiResultDetectedDto,
        newItem: AiResultDetectedDto
    ): Boolean {
        return oldItem.detectedTitle == newItem.detectedTitle
    }

    override fun areContentsTheSame(
        oldItem: AiResultDetectedDto,
        newItem: AiResultDetectedDto
    ): Boolean {
        return oldItem == newItem
    }
}