package com.bocdoc.anydoc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bocdoc.anydoc.data.dto.AiResultDetectedDto
import com.bocdoc.anydoc.databinding.ItemAiResultImgBinding
import com.bocdoc.anydoc.presentation.viewholder.AiResultImgViewHolder

class AiResultImgAdapter :
    ListAdapter<String, AiResultImgViewHolder>(
        AiResultImgDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AiResultImgViewHolder {
        val binding = ItemAiResultImgBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AiResultImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AiResultImgViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AiResultImgDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }
}