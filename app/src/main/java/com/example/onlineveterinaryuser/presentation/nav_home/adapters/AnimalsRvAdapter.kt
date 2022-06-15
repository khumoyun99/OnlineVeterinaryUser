package com.example.onlineveterinaryuser.presentation.nav_home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemRvAnimalsBinding
import com.example.onlineveterinaryuser.presentation.nav_home.models.Animal

class AnimalsRvAdapter(val listener : OnItemOnlyAnimalsListener):
    RecyclerView.Adapter<AnimalsRvAdapter.MyOnlyAnimalsViewHolder>() {

    inner class MyOnlyAnimalsViewHolder(private val itemRvAnimalsBinding : ItemRvAnimalsBinding):
        RecyclerView.ViewHolder(itemRvAnimalsBinding.root) {
        init {
            itemView.setOnClickListener {
                listener.onClick(differConfig.currentList[absoluteAdapterPosition])
            }
        }

        fun onBind(animal : Animal) {
            itemRvAnimalsBinding.apply {
                imgAnimalPhoto.setImageResource(animal.img)
            }
        }
    }

    fun mySubmitList(animalList : ArrayList<Animal>) {
        differConfig.currentList.clear()
        differConfig.submitList(animalList)

    }

    private val diffCallBack = object:DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem : Animal , newItem : Animal) : Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem : Animal , newItem : Animal) : Boolean {
            return oldItem == newItem
        }
    }

    private val differConfig = AsyncListDiffer(this , diffCallBack)

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyOnlyAnimalsViewHolder {
        return MyOnlyAnimalsViewHolder(
            ItemRvAnimalsBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder : MyOnlyAnimalsViewHolder , position : Int) {
        holder.onBind(differConfig.currentList[position])
    }

    override fun getItemCount() : Int {
        return differConfig.currentList.size
    }

    interface OnItemOnlyAnimalsListener {
        fun onClick(animal : Animal)
    }
}