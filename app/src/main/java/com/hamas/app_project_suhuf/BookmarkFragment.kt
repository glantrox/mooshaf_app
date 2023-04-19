package com.hamas.app_project_suhuf

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView

import com.hamas.app_project_suhuf.database.Bookmark
import com.hamas.app_project_suhuf.database.BookmarkDatabase
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import hamas.app_project_suhuf.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class BookmarkFragment : BottomSheetDialogFragment() {

    private lateinit var toolbar: MaterialToolbar

    private val btnDelete by lazy {
        toolbar.menu.findItem(R.id.btn_deleteAllBookmark)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? = inflater.inflate(R.layout.activity_bookmark_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.toolbar_bookmark)

        val database = BookmarkDatabase.getInstance(requireContext())
        val recycleView = view.findViewById<RecyclerView>(R.id.rv_bookmark)
        val textbookmarkEmpty = view.findViewById<TextView>(R.id.tv_bkEmpty)
        val myVib = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator



        lifecycleScope.launch {
            val bookmarkList: Flow<List<Bookmark>> = database.QuranDao().getBookmarks()
            bookmarkList.collect {
                if (it.isEmpty()) {
                    textbookmarkEmpty.isVisible = true
                    btnDelete.isVisible = false
                }
            }
        }




        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.btn_dismissSetting) {
                dismiss()
            } else if (it.itemId == R.id.btn_deleteAllBookmark) {
                lifecycleScope.launch {
                    val bookmarkList: Flow<List<Bookmark>> = database.QuranDao().getBookmarks()
                    bookmarkList.collect {
                        if (it.isEmpty()) {
                            Toast.makeText(requireContext(),"Bookmark is Empty!", Toast.LENGTH_SHORT).show()
                        } else {
                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle("Delete All Bookmarks")
                                .setMessage("Are you sure want to delete all Bookmarks?")
                                .setNegativeButton("Cancel") { dialog, _ ->
                                    dialog.dismiss()
                                }
                                .setPositiveButton("Confirm") { dialog, _ ->
                                    lifecycleScope.launch {
                                        val bookmarkDao =
                                            BookmarkDatabase.getInstance(requireContext())
                                                .QuranDao()
                                        bookmarkDao.deleteAllBookmark()
                                        Toast.makeText(
                                            requireContext(),
                                            "All Bookmark Deleted!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        myVib.vibrate(25)
                                        delay(10)
                                        myVib.vibrate(25)
                                        dismiss()
                                    }
                                }
                                .show()
                                .create()
                        }
                    }
                }
            }

            true
        }

        lifecycleScope.launch {
            val bookmarkList: Flow<List<Bookmark>> = database.QuranDao().getBookmarks()
            bookmarkList.collect {
                val adapter = BookmarkAdapter(it)
                recycleView.adapter = adapter

                adapter.deleteBookmarkListener = {
                    lifecycleScope.launch {
                        val bookmarkDao = BookmarkDatabase.getInstance(requireContext()).QuranDao()
                        bookmarkDao.deleteBookmark(it)
                    }
                    myVib.vibrate(17)
                    Toast.makeText(requireContext(), "Bookmark Deleted", Toast.LENGTH_SHORT).show()
                }

                adapter.itemClickListener = {
                    val intent = Intent(requireContext(), QuranActivity::class.java)
                    intent.putExtra(QuranActivity.SURAH_NUMBER, it.surahNumber)
                    intent.putExtra(QuranActivity.AYAH_NUMBER, it.ayatNumber)
                    startActivity(intent)
                    dismiss()
                }
            }

        }


//       fun setBookmarkAdapter(bookmark: Flow<List<Bookmark>>) {
//           lifecycleScope.launch {
//               val adapter = BookmarkAdapter(bookmark)
//               recycleView.adapter = adapter
//
//           }
//       }
//
//        lifecycleScope.launch {
//            val bookmark = database.QuranDao().getBookmarks()
//            setBookmarkAdapter(bookmark)
//        }


    }

    companion object {
        const val BOOKMARK_NUMBER = "bookmark"
    }
}