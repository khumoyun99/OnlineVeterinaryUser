package com.example.onlineveterinaryuser.presentation.nav_doctors.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineveterinaryuser.databinding.ItemRvJobExperienceBinding
import com.example.onlineveterinaryuser.presentation.nav_doctors.models.Work

class JobExperienceRvAdapter:RecyclerView.Adapter<JobExperienceRvAdapter.MyJobExperienceVH>() {

    inner class MyJobExperienceVH(private val itemRvJobExperienceBinding : ItemRvJobExperienceBinding):
        RecyclerView.ViewHolder(itemRvJobExperienceBinding.root) {


        @SuppressLint("SetTextI18n")
        fun onBind(work : Work) {
            itemRvJobExperienceBinding.apply {
                tvNumber.text = (absoluteAdapterPosition + 1).toString()
                tvWorkplace.text = work.workPlace
                tvProfession.text = work.profession
                tvWorkingPeriod.text = work.periodTime
                tvWorkplaceAddress.text = work.workingPlaceAddress
            }

        }
    }

    fun mySubmitList(worksList : ArrayList<Work>) {
        differConfig.currentList.clear()
        differConfig.submitList(worksList)
    }

    private val diffCallBack = object:DiffUtil.ItemCallback<Work>() {
        override fun areItemsTheSame(oldItem : Work , newItem : Work) : Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem : Work , newItem : Work) : Boolean {
            return oldItem == newItem
        }
    }

    private val differConfig = AsyncListDiffer(this , diffCallBack)

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyJobExperienceVH {
        return MyJobExperienceVH(
            ItemRvJobExperienceBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder : MyJobExperienceVH , position : Int) {
        holder.onBind(differConfig.currentList[position])
    }

    override fun getItemCount() : Int {
        return differConfig.currentList.size
    }
}