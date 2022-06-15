package com.example.onlineveterinaryuser.presentation.nav_home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemRvAllAnimalsBinding
import com.example.onlineveterinaryuser.presentation.nav_home.models.AllAnimals
import com.example.onlineveterinaryuser.presentation.nav_home.models.Animal

class AllAnimalsRvAdapter(val listener : OnAllAnimalsClickListener):
    RecyclerView.Adapter<AllAnimalsRvAdapter.MyAllAnimalsVH>() {

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyAllAnimalsVH {
        return MyAllAnimalsVH(
            ItemRvAllAnimalsBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    }

    override fun onBindViewHolder(holder : MyAllAnimalsVH , position : Int) {
        holder.onBind(differConfig.currentList[position])
    }

    override fun getItemCount() : Int {
        return differConfig.currentList.size
    }

    fun mySubmitList(allAnimalsList : ArrayList<AllAnimals>) {
        differConfig.currentList.clear()
        differConfig.submitList(allAnimalsList)
    }

    private val diffCallBack = object:DiffUtil.ItemCallback<AllAnimals>() {
        override fun areItemsTheSame(oldItem : AllAnimals , newItem : AllAnimals) : Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem : AllAnimals , newItem : AllAnimals) : Boolean {
            return oldItem == newItem
        }
    }

    private val differConfig = AsyncListDiffer(this , diffCallBack)

    inner class MyAllAnimalsVH(private val itemRvAllAnimals : ItemRvAllAnimalsBinding):
        RecyclerView.ViewHolder(itemRvAllAnimals.root) {

        private lateinit var animalsRvAdapter : AnimalsRvAdapter

        fun onBind(allAnimals : AllAnimals) {
            animalsRvAdapter = AnimalsRvAdapter(object:AnimalsRvAdapter.OnItemOnlyAnimalsListener {
                override fun onClick(animal : Animal) {
                    listener.onItemClick(animal)
                }
            })
            itemRvAllAnimals.apply {
                itemRvAllAnimals.apply {
                    tvAnimalType.text = allAnimals.type
                    animalsRvAdapter.mySubmitList(allAnimals.animalsList)
                    rvAnimals.adapter = animalsRvAdapter
                }
            }
        }
    }

    interface OnAllAnimalsClickListener {
        fun onItemClick(animal : Animal)
    }
}