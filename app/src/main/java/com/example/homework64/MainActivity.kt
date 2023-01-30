package com.example.homework64

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
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
//        if (binding.editHash.text.isEmpty()){
//            adapter.isEditEmpty(true)
//        }
//        if (binding.editHash.text.isEmpty()){
//            binding.recyclerHash.isVisible = false
//        }else{
//            binding.recyclerHash.isVisible = true
//        }

        binding.editHash.doOnTextChanged { text, start, before, count ->
            adapter.addListToshow(text.toString())
            //adapter.addListToshow(getHashTagFromEditText(text))
        }
        binding.editHash.doBeforeTextChanged { text, start, count, after ->
            adapter.addListToshow(text.toString())
        }
//        binding.editHash.doAfterTextChanged {
//            adapter.addListToshow(binding.editHash.toString())
//        }

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
                    adapter.addHashTag(getHashTagFromEditText(binding.editHash.text.toString()))
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

    private fun getHashTagFromEditText(comeText:String):ArrayList<String> {
        var result = arrayListOf<String>()
        var hash = 0
        val textEdit = comeText
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
            if (hash < 2) {
                val hashList: List<String> = it.split(Regex("(?=#)"))
                hashList.forEach {hash ->
                    if (hash.contains("#")) {
                        if (hash.length > 1) {
                            result.add(hash)
                        }
                    }
                }
            }
            hash = 0
        }
        hashList.clear()
        return result
    }
}