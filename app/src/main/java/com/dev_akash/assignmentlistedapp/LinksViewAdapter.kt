package com.dev_akash.assignmentlistedapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_akash.assignmentlistedapp.data.LinksDto
import com.dev_akash.assignmentlistedapp.databinding.ItemLinkViewBinding
import com.dev_akash.assignmentlistedapp.utils.ClipboardService
import com.dev_akash.assignmentlistedapp.utils.ViewExtensions.loadImage

class LinksViewAdapter() : RecyclerView.Adapter<LinksViewAdapter.LinksViewVH>() {

    var links: List<LinksDto> = arrayListOf()

    inner class LinksViewVH(private val binding: ItemLinkViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LinksDto) {
            binding.apply {
                icon.loadImage(item.originalImage)
                tvTitle.text = item.title
                tvSubTitle.text = ""
                tvClicks.text = item.totalClicks.toString()

                tvLink.text = item.webLink

                ivCopy.setOnClickListener {
                    ClipboardService.copyToClipboard(binding.root.context,tvLink.text.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewVH {
        return LinksViewVH(
            ItemLinkViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = links.size

    override fun onBindViewHolder(holder: LinksViewVH, position: Int) {
        holder.bind(links[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList:List<LinksDto>){
        links = newList
        notifyDataSetChanged()
    }
}