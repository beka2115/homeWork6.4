package com.example.homework64

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework64.databinding.ItemHashBinding

class HashTagAdapter : RecyclerView.Adapter<HashTagAdapter.HashTagViewHolder>() {

    private val hashList: ArrayList<String> = arrayListOf()
    private var startText: String? = null

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
        return hashList.size
    }

    override fun onBindViewHolder(holder: HashTagViewHolder, position: Int) {
        holder.bind(hashList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addHashTag(hash: String) {
        hashList.add(hash)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getEditText(text: String) {
        startText = text
        notifyDataSetChanged()
    }

    inner class HashTagViewHolder(private val binding: ItemHashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hash: String) {
            binding.textHash.text = hash

//            if (hash.startsWith(startText.toString())) {
//                // Log.e("ololo",startText.toString())
//                binding.textHash.text = hash
//            }
        }
//        hashList.forEach {
//            if (it.startsWith("#he")) {
//                Log.e("ololo",it)
//            }
//        }


    }
}