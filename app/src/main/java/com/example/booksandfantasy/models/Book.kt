package com.example.booksandfantasy.models

typealias Books = ArrayList<Book>

data class Book(
    var name: String
) {
    var id: Int? = null
    var author: String? = null
    var editorial: String? = null
    var imagen: String? = null
    var sinopsis: String? = null
    var isbn: String? = null
    var calificacion: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null
    var genero: List<Genero> = emptyList()
}