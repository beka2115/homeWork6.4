package com.example.homework64

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework64.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = HashTagAdapter()
    private var hashList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        addHash()
    }

    private fun addHash() {
        with(binding) {
            btnSend.setOnClickListener {
                if (editHash.text.isNotEmpty()) {
                    getHashTagFromEditText()
                }
                editHash.text.clear()
            }
        }
    }

    private fun initRecycler() {
        binding.apply {
            recyclerHash.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerHash.adapter = adapter
        }
    }

    private fun getHashTagFromEditText() {
        val textEdit = binding.editHash.text.toString()
        val listHash: List<String> = textEdit?.split(Regex("(?=#)"))!!
        listHash.forEach {
            hashList.add(it)
        }
        hashList.forEach {
            val list: List<String> = it?.split(" ")!!.map { it -> it.trim() }
            list.forEach { it ->
                if (it.contains("#")) {
                    if (it.length > 1) {
                        adapter.addHashTag(it)
                    }
                }
            }
        }
        hashList.clear()
    }
}