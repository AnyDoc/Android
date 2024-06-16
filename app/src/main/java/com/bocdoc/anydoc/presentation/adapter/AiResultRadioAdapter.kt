package com.bocdoc.anydoc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bocdoc.anydoc.coreui.view.ItemClick
import com.bocdoc.anydoc.databinding.ItemAiResultRadioBinding
import com.bocdoc.anydoc.presentation.viewholder.AiResultRadioViewHolder

class AiResultRadioAdapter :
    ListAdapter<String, AiResultRadioViewHolder>(
        AiResultRadioDiffCallback()
    ) {

    var aiResultRadioClick: ItemClick? = null
    var selectedPosition = 0

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
            selectedPosition = position
        }
    }
}

class AiResultRadioDiffCallback : DiffUtil.ItemCallback<String>() {
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