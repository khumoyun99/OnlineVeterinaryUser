package com.example.onlineveterinaryuser.presentation.nav_animals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemMyAnimalsBinding
import com.example.onlineveterinaryuser.presentation.nav_animals.models.MyAnimal

class MyAnimalsRvAdapter(val listener : OnMyAnimalsTouchListener):
    RecyclerView.Adapter<MyAnimalsRvAdapter.MyAnimalsViewHolder>() {

    private val animalsList = mutableListOf<MyAnimal>()

    fun mySubmitList(animalList : ArrayList<MyAnimal>) {
        animalsList.apply {
            clear()
            addAll(animalList)
        }
    }

    inner class MyAnimalsViewHolder(private val itemMyAnimalsBinding : ItemMyAnimalsBinding):
        RecyclerView.ViewHolder(itemMyAnimalsBinding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(animalsList[absoluteAdapterPosition])
            }
        }

        fun onBind(animal : MyAnimal) {
            itemMyAnimalsBinding.apply {
                imgMyAnimals.setBackgroundResource(animal.img)
                tvMyAnimalsName.text = animal.name
            }

        }
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyAnimalsViewHolder {
        return MyAnimalsViewHolder(
            ItemMyAnimalsBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder : MyAnimalsViewHolder , position : Int) {
        holder.onBind(animalsList[position])
    }

    override fun getItemCount() : Int {
        return animalsList.size
    }

    interface OnMyAnimalsTouchListener {
        fun onItemClick(animal : MyAnimal)
    }
}