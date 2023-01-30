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
        onEditTextChange()
    }

    fun onEditTextChange() {
//        binding.editHash.doOnTextChanged { text, start, before, count ->
//            if (text!!.contains("#")) {
//                val listHash: List<String> = text?.split(Regex("(?=#)"))!!
//                listHash.forEach {
//                    if (it.contains("#")) {
//
//                        Log.e("ololo", it)
//                        adapter.getEditText(it.toString())
//                    }
//                }
//            }
//            // adapter.getEditText(text.toString())
//        }
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
        var hash = 0
        val textEdit = binding.editHash.text.toString()
        val listHash: List<String> = textEdit?.split(" ")!!.map { it -> it.trim() }
        listHash.forEach {
            if (it.contains("#")) {
                hashList.add(it)
            }
        }

        hashList.forEach {
            val chars = it.toCharArray()
            chars.forEach {
                if (it == '#') {
                    hash++
                }
            }


            Log.e("ololo", hash.toString())
            if (hash < 2) {
                val hashList: List<String> = it.split(Regex("(?=#)"))
                hashList.forEach {
                    if (it.contains("#")) {
                        if (it.length > 1) {
                            adapter.addHashTag(it)
                            Log.e("ololo", it)
                        }
                    }
                }
            }
            hash = 0
        }
        hashList.clear()

    }
}