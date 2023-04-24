package com.dev_akash.assignmentlistedapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_akash.assignmentlistedapp.data.Stats
import com.dev_akash.assignmentlistedapp.databinding.ItemStatsBinding
import com.dev_akash.assignmentlistedapp.utils.ViewExtensions.loadImage

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.StatsVH>() {

    private var statList: List<Stats> = arrayListOf()

    inner class StatsVH(private val binding: ItemStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Stats) {
            binding.apply {
                icon.loadImage(item.img)
                tvData.text = item.data
                tvTitle.text = item.title
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Stats>) {
        statList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsVH {
        return StatsVH(
            ItemStatsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = statList.size

    override fun onBindViewHolder(holder: StatsVH, position: Int) {
        holder.bind(statList[position])
    }
}