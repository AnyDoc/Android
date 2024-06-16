package com.bocdoc.anydoc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bocdoc.anydoc.coreui.view.ItemClick
import com.bocdoc.anydoc.data.dto.AiResultRadioDto
import com.bocdoc.anydoc.databinding.ItemAiResultRadioBinding
import com.bocdoc.anydoc.presentation.viewholder.AiResultRadioViewHolder

class AiResultRadioAdapter :
    ListAdapter<AiResultRadioDto, AiResultRadioViewHolder>(
        AiResultRadioDiffCallback()
    ) {

    var aiResultRadioClick: ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AiResultRadioViewHolder {
        val binding = ItemAiResultRadioBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AiResultRadioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AiResultRadioViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { view ->
            aiResultRadioClick?.onClick(view, position)
        }
    }
}

class AiResultRadioDiffCallback : DiffUtil.ItemCallback<AiResultRadioDto>() {
    override fun areItemsTheSame(
        oldItem: AiResultRadioDto,
        newItem: AiResultRadioDto
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AiResultRadioDto,
        newItem: AiResultRadioDto
    ): Boolean {
        return oldItem == newItem
    }
}