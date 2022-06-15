package com.example.onlineveterinaryuser.presentation.nav_home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ItemVpAnimalsBinding


class VeterinaryImagesVpAdapter:
    RecyclerView.Adapter<VeterinaryImagesVpAdapter.MyViewPagerViewHolder>() {

    private val animalsList = listOf(
        R.drawable.img_vet1 ,
        R.drawable.img_vet2 ,
        R.drawable.img_vet3 ,
        R.drawable.img_vet4 ,
        R.drawable.img_vet5 ,
        R.drawable.img_vet6 ,
    )

    inner class MyViewPagerViewHolder(private val itemVpAnimalsBinding : ItemVpAnimalsBinding):
        RecyclerView.ViewHolder(itemVpAnimalsBinding.root) {

        fun onBind(img : Int) {
            itemVpAnimalsBinding.apply {
                ivVpAnimals.setImageResource(animalsList[img])
            }
        }

    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyViewPagerViewHolder {
        return MyViewPagerViewHolder(ItemVpAnimalsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder : MyViewPagerViewHolder , position : Int) {
        holder.onBind(position)
    }

    override fun getItemCount() : Int {
        return animalsList.size
    }
}