package com.example.onlineveterinaryuser.presentation.nav_medicine.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ItemRvMedicineBinding
import com.example.onlineveterinaryuser.presentation.nav_medicine.models.Medicines
import com.squareup.picasso.Picasso

class MedicineRvAdapter(val listener : OnItemTouchClickListener):
    RecyclerView.Adapter<MedicineRvAdapter.MyMedicineVH>() {

    inner class MyMedicineVH(private val itemRvMedicineBinding : ItemRvMedicineBinding):
        RecyclerView.ViewHolder(itemRvMedicineBinding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItem(differConfig.currentList[absoluteAdapterPosition])
            }
        }

        fun onBind(medicines : Medicines) {
            itemRvMedicineBinding.apply {
//                Picasso.get().load(medicines.imageUrl).error(R.drawable.img_default_medicine_photo)
                tvMedicineName.text = medicines.name
                tvMedicineDescription.text = medicines.description
                tvMedicinesDoctorsName.text = medicines.doctorsName
                tvMedicinePrise.text = medicines.price
            }

        }
    }

    fun mySubmitList(medicineList : ArrayList<Medicines>) {
        differConfig.submitList(medicineList)
    }

    private var DIFF_CALLBACK = object:DiffUtil.ItemCallback<Medicines>() {
        override fun areItemsTheSame(oldItem : Medicines , newItem : Medicines) : Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem : Medicines , newItem : Medicines) : Boolean {
            return oldItem == newItem
        }
    }

    private val differConfig = AsyncListDiffer(this , DIFF_CALLBACK)

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyMedicineVH {
        return MyMedicineVH(
            ItemRvMedicineBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }


    override fun onBindViewHolder(holder : MyMedicineVH , position : Int) {
        holder.onBind(differConfig.currentList[position])
    }

    override fun getItemCount() : Int {
        return differConfig.currentList.size
    }

    interface OnItemTouchClickListener {
        fun onItem(medicines : Medicines)
    }

}