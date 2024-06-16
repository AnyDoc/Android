package com.bocdoc.anydoc.presentation.addImage

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.databinding.ItemAddImageBinding

class AddImgAdapter(private val imgList: ArrayList<Uri>) :
    RecyclerView.Adapter<AddImgAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemAddImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imgUri: Uri) {
            binding.ivAddImage.load(imgUri)
        }

        fun bindDefault() {
            binding.ivAddImage.setImageResource(R.drawable.ic_image_add_frame)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemAddImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = imgList.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == DEFAULT_IMAGE_POSITION) {
            holder.bindDefault()
        } else {
            holder.bind(imgList[adjustedPosition(position)])
        }
    }

    private fun adjustedPosition(position: Int) = position - 1

    companion object {
        const val DEFAULT_IMAGE_POSITION = 0
    }
}