package com.example.booksandfantasy.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksandfantasy.repository.BooksRepository

import com.example.booksandfantasy.R
import com.example.booksandfantasy.databinding.ActivityMainBinding
import com.example.booksandfantasy.models.Book

import com.example.booksandfantasy.models.Books
import com.example.booksandfantasy.ui.viewmodels.MainViewModel
import com.example.booksandfantasy.ui.adapters.BookAdapter

class MainActivity : AppCompatActivity(), BookAdapter.OnBookClickListener {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaPersonas()
    }
    private fun setupEventListeners() {
        binding.btnInsBook.setOnClickListener {
            val intent = Intent(this, BooksDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewModelListeners() {
        model.bookList.observe(this) {
            val adapter = (binding.lstLibros.adapter as BookAdapter)
            adapter.updateData(it)
        }
    }


    private fun setupRecyclerView() {
        binding.lstLibros.apply {
            this.adapter = BookAdapter(Books(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onBookClick(book: Book) {
        val intent = Intent(this, BooksDetailActivity::class.java)
        intent.putExtra("bookId", book.id)
        startActivity(intent)
    }

    override fun onBookDelete(book: Book) {
        BooksRepository.deleteBooks(book.id!!,
            success = {
                model.fetchListaPersonas()
            },
            failure = {
                it.printStackTrace()
            })
    }
}