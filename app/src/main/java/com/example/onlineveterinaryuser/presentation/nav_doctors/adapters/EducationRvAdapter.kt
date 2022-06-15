package com.example.onlineveterinaryuser.presentation.nav_doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemRvEducationBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Education

class EducationRvAdapter:RecyclerView.Adapter<EducationRvAdapter.MyEducationVH>() {

    inner class MyEducationVH(private val itemRvEducationBinding : ItemRvEducationBinding):
        RecyclerView.ViewHolder(itemRvEducationBinding.root) {

        fun onBind(education : Education) {
            itemRvEducationBinding.apply {
                tvEducationNumber.text = (absoluteAdapterPosition + 1).toString()
                tvNamePlaceOfStudy.text = education.namePlaceOfStudy
                tvDegree.text = education.degree
                tvEducationPeriod.text = education.period
                tvNamePlaceOfStudyAddress.text = education.address
            }

        }
    }

    fun mySubmitList(educationList : ArrayList<Education>) {
        differConfig.currentList.clear()
        differConfig.submitList(educationList)
    }

    val diffCallBack = object:DiffUtil.ItemCallback<Education>() {
        override fun areItemsTheSame(oldItem : Education , newItem : Education) : Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem : Education , newItem : Education) : Boolean {
            return oldItem == newItem
        }
    }

    private val differConfig = AsyncListDiffer(this , diffCallBack)

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyEducationVH {
        return MyEducationVH(
            ItemRvEducationBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder : MyEducationVH , position : Int) {
        holder.onBind(differConfig.currentList[position])
    }

    override fun getItemCount() : Int {
        return differConfig.currentList.size
    }


}