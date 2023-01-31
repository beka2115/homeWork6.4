package com.example.homework64

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.homework64.databinding.ItemHashBinding

class HashTagAdapter : RecyclerView.Adapter<HashTagAdapter.HashTagViewHolder>() {

    private val listToShow: ArrayList<String> = arrayListOf()
    private val hashList: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashTagViewHolder {
        return HashTagViewHolder(
            ItemHashBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listToShow.size
    }

    override fun onBindViewHolder(holder: HashTagViewHolder, position: Int) {
        holder.bind(listToShow[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addHashTag(hash:String) {
        hashList.add(hash)
        notifyDataSetChanged()
    }

    fun addListToshow(text: String) {
        listToShow.clear()
        if (text.contains("#")) {
            hashList.forEach {
                if (it.startsWith(text)) {
                    listToShow.add(it)
                }
            }
        }
        notifyDataSetChanged()
    }

    inner class HashTagViewHolder(private val binding: ItemHashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hash: String) {
            binding.textHash.text = hash
        }
    }
}