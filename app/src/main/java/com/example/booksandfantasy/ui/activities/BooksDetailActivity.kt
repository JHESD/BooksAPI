package com.example.booksandfantasy.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.booksandfantasy.databinding.ActivityBooksDetailBinding
import com.example.booksandfantasy.ui.viewmodels.BooksDetailViewModel

class BooksDetailActivity : AppCompatActivity() {
    private var id: Int = 0
    lateinit var binding: ActivityBooksDetailBinding
    private val model: BooksDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBooksDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

        id = intent.getIntExtra("booksId", -1)
        if (id != -1) {
            model.loadbooks(id)
        }
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
        model.books.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.txtCategoryName.editText?.setText(it.nombre)
        }
    }

    private fun setupEventListeners() {
        binding.btnSaveBooks.setOnClickListener {
            model.saveCategory(binding.txtCategoryName.editText?.text.toString(), id)
        }
    }
}