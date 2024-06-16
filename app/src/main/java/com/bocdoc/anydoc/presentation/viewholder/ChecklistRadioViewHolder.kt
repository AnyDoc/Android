package com.bocdoc.anydoc.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bocdoc.anydoc.data.dto.CheckListDto
import com.bocdoc.anydoc.databinding.ItemChecklistBinding

class ChecklistRadioViewHolder(private val binding: ItemChecklistBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(detectedDto: CheckListDto) {
        itemView.setOnClickListener {
            // TODO 통신 구현 시, 달라진 경우 통신 보내도록 조치하기
            binding.tvChecklistItemRadio.isSelected = !binding.tvChecklistItemRadio.isSelected
        }

        with(binding) {
            tvChecklistItemTitle.text = detectedDto.title
            tvChecklistItemDescription.text = detectedDto.description
            tvChecklistItemRadio.isSelected = detectedDto.check
        }
    }
}