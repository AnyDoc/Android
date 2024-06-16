package com.bocdoc.anydoc.presentation.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bocdoc.anydoc.databinding.ItemVetClinicsMapBinding

class VetClinicsAdapter(private val vetClinicsList: List<VetClinics>) : RecyclerView.Adapter<VetClinicsAdapter.RouteViewHolder>() {

    inner class RouteViewHolder(val vetClinicsMapBinding: ItemVetClinicsMapBinding) : RecyclerView.ViewHolder(vetClinicsMapBinding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val view = ItemVetClinicsMapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val currentItem = vetClinicsList[position]

        with(holder.vetClinicsMapBinding){
            ivVetClinicsImage.load(currentItem.image)
            tvVetClinicsName.text = currentItem.name
            tvVetClinicsDate.text = currentItem.date
            tvVetClinicsPhone.text = currentItem.phone
        }
    }

    override fun getItemCount(): Int {
        return vetClinicsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}