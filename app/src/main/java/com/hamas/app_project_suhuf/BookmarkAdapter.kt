package com.hamas.app_project_suhuf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.hamas.app_project_suhuf.database.Bookmark
import hamas.app_project_suhuf.R


class BookmarkAdapter(val bookmarkList: List<Bookmark>): RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    var itemClickListener: ((Bookmark) -> Unit)? = null
    var deleteBookmarkListener: ((Bookmark) -> Unit)? = null
    class BookmarkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(bookmark: Bookmark) {
            textNoAyah.text = "${bookmark.ayatNumber}"
            textSurahName.text = "${bookmark.surahName}"
        }

        val textNoAyah = itemView.findViewById<TextView>(R.id.tv_ayahNo_bookmark)
        val textSurahName = itemView.findViewById<TextView>(R.id.tv_surahName_bookmark)
        val btnDeleteBookmark = itemView.findViewById<ImageView>(R.id.btn_deleteBookmark)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false)
        return BookmarkAdapter.BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark: Bookmark = bookmarkList[position]
        holder.bindView(bookmark)
        holder.btnDeleteBookmark.setOnClickListener {
            deleteBookmarkListener?.invoke(bookmark)
        }
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(bookmark)
        }
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }


}