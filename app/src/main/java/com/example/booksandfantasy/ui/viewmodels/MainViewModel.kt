package com.example.booksandfantasy.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksandfantasy.models.Books
import com.example.booksandfantasy.repository.BooksRepository

class MainViewModel : ViewModel(){
    private val _bookList: MutableLiveData<Books> by lazy {
        MutableLiveData<Books>(Books())
    }
    val bookList: LiveData<Books> get() = _bookList


    fun fetchListaPersonas() {
        BooksRepository.getBooksList(
            success = { personas ->
                personas?.let {

                    _bookList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            })

    }
}