package com.example.booksandfantasy.models

typealias Generos = List<Genero>

class Genero(
    var name: String
) {
    var id: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null
    var pivot: List<Pivot> = emptyList()
}