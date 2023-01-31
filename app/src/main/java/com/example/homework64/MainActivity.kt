package com.example.homework64

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework64.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = HashTagAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        addHash()
        onEditTextChange()
    }

    fun onEditTextChange() {
        binding.editHash.doOnTextChanged { text, start, before, count ->
            adapter.addListToshow(text.toString())
        }
        binding.editHash.doBeforeTextChanged { text, start, count, after ->
            adapter.addListToshow(text.toString())
        }
    }

    private fun initRecycler() {
        binding.apply {
            recyclerHash.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerHash.adapter = adapter
        }
    }

    private fun addHash() {
        with(binding) {
            btnSend.setOnClickListener {
                if (editHash.text.isNotEmpty()) {
                    splitTextToReadyHash(splitBySpace(binding.editHash.text.toString())).forEach {
                        if (it.contains("#")){
                            if (it.length> 1 ){
                                adapter.addHashTag(it)
                            }
                        }
                    }
                }
                editHash.text.clear()
            }
        }
    }
    fun splitBySpace(text: String):ArrayList<String>{
        var result = arrayListOf<String>()
        val listHash: List<String> = text?.split(" ")!!.map { it -> it.trim() }
        listHash.forEach {
            if (it.contains("#")) {
                result.add(it)
            }
        }
        return result
    }

    fun splitTextToReadyHash(list:ArrayList<String>):ArrayList<String>{
        var result = arrayListOf<String>()
        var hash=0
        list.forEach {
            val chars = it.toCharArray()
            chars.forEach {
                if (it == '#') {
                    hash++
                }
            }
            if (hash < 2) {
                val hashList: List<String> = it.split(Regex("(?=#)")).map { it -> it.trim()}
                hashList.forEach {hash ->
                   result.add(hash)
                }
            }
            hash = 0
        }
        return result
    }

}