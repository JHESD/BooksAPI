package com.example.booksandfantasy.api

import com.example.booksandfantasy.models.Book
import com.example.booksandfantasy.models.Books


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface API_BooksService {

    //---Libros---
    @GET("books")
    fun getBooks(
    ): Call<Books>

    @GET("books/{id}")
    fun getBooksById(
        @Path("id") id: Int
    ): Call<Book?>

    @POST("books")
    fun insertBooks(
        @Body books: Book
    ): Call<Book>

    @PUT("books/{id}")
    fun updateBooks(
        @Path("id") id: Book,
        @Body book: Int
    ): Call<Book>

    @DELETE("books/{id}")
    fun deleteBooks(
        @Path("id") id: Int
    ): Call<Void>


    //---Generos---

}
