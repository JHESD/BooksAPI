package com.example.booksandfantasy.repository

import com.example.booksandfantasy.models.Book
import com.example.booksandfantasy.api.API_BooksService
import com.example.booksandfantasy.models.Books
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object BooksRepository {

    fun getBooksList(success: (Books?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = RetrofitBuilder.RetrofitBuilderWows

        val service: API_BooksService =
            retrofit.create(API_BooksService::class.java)
        service.getBooks().enqueue(object : Callback<Books> {
            override fun onResponse(res: Call<Books>, response: Response<Books>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Books>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertBooks(
        Bookss: Book,
        success: (Book) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitBuilder.RetrofitBuilderWows

        val service: API_BooksService =
            retrofit.create(API_BooksService::class.java)
        service.insertBooks(Bookss).enqueue(object : Callback<Book> {
            override fun onResponse(res: Call<Book>, response: Response<Book>) {
                val objCategory = response.body()
                success(objCategory!!)
            }

            override fun onFailure(res: Call<Book>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getBooks(id: Int, success: (Book?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitBuilder.RetrofitBuilderWows

        val service: API_BooksService =
            retrofit.create(API_BooksService::class.java)
        service.getBooksById(id).enqueue(object : Callback<Book?> {
            override fun onResponse(res: Call<Book?>, response: Response<Book?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Book?>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateBooks(
        Bookss: Book,
        success: (Book) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitBuilder.RetrofitBuilderWows

        val service: API_BooksService =
            retrofit.create(API_BooksService::class.java)
        val BookssId = Bookss.id ?: 0
        service.updateBooks(Bookss, BookssId).enqueue(object : Callback<Book> {
            override fun onResponse(res: Call<Book>, response: Response<Book>) {
                val objCategory = response.body()!!
                success(objCategory)
            }

            override fun onFailure(res: Call<Book>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteBooks(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitBuilder.RetrofitBuilderWows

        val service: API_BooksService =
            retrofit.create(API_BooksService::class.java)
        service.deleteBooks(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}