package com.example.booksandfantasy.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.booksandfantasy.databinding.BookItemLayoutBinding

import com.example.booksandfantasy.models.Book
import com.example.booksandfantasy.models.Books

class BookAdapter (val BookList: Books, val listener: OnBookClickListener) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding =
            BookItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BookViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return BookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val persona = BookList[position]
        holder.bind(persona, listener)
    }

    fun updateData(BookList: Books) {
        this.BookList.clear()
        this.BookList.addAll(BookList)
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Book, listener: OnBookClickListener) {
            val binding = BookItemLayoutBinding.bind(itemView)
            binding.apply {
                txtNameLibro.text = book.name
                /*btnDeleteCategory.setOnClickListener {
                    listener.onBookDelete(book)
                }
                lblCategoryName.setOnClickListener {
                    listener.onBookClick(book)
                }
                root.setOnClickListener { listener.onBookClick(book) }*/
            }

        }
    }

    interface OnBookClickListener {

        fun onBookClick(book: Book)
        fun onBookDelete(book: Book)
    }
}