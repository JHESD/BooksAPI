package com.example.booksandfantasy.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksandfantasy.models.Book
import com.example.booksandfantasy.repository.BooksRepository

class BooksDetailViewModel : ViewModel(){

    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _Books: MutableLiveData<Book?> by lazy {
        MutableLiveData<Book?>(null)
    }
    val category: LiveData<Book?> get() = _Books

    fun saveCategory(name: String, id: Int) {
        val Booky = Book(
            name = name
        )
        if (id != 0) {
            Booky.id = id
            BooksRepository.updateBooks(Booky,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            BooksRepository.insertBooks(Booky,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }

    fun loadCategory(id: Int) {
        BooksRepository.getBooks(id,
            success = {
                _Books.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
}