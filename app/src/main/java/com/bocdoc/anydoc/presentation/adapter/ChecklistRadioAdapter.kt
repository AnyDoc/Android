package com.bocdoc.anydoc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bocdoc.anydoc.data.dto.CheckListDto
import com.bocdoc.anydoc.databinding.ItemChecklistBinding
import com.bocdoc.anydoc.presentation.viewholder.ChecklistRadioViewHolder

class ChecklistRadioAdapter :
    ListAdapter<CheckListDto, ChecklistRadioViewHolder>(
        CheckListInfoDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChecklistRadioViewHolder {
        val binding = ItemChecklistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChecklistRadioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChecklistRadioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CheckListInfoDiffCallback : DiffUtil.ItemCallback<CheckListDto>() {
    override fun areItemsTheSame(
        oldItem: CheckListDto,
        newItem: CheckListDto
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: CheckListDto,
        newItem: CheckListDto
    ): Boolean {
        return oldItem == newItem
    }
}