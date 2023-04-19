package com.hamas.app_project_suhuf




import android.annotation.SuppressLint
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load

import com.hamas.app_project_suhuf.database.Surah
import com.hamas.app_project_suhuf.database.SurahViewmodel
import com.hamas.app_quran.data.QuranDataBase
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.l4digital.fastscroll.FastScrollRecyclerView
import hamas.app_project_suhuf.R
import kotlinx.coroutines.launch


class FragmentQuran: Fragment(R.layout.fragment_quran) {

    private lateinit var recyclerView : FastScrollRecyclerView
    private val viewModel: SurahViewmodel by activityViewModels()


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    recyclerView = view.findViewById(R.id.recyclerview_surah)






        val database = QuranDataBase.getInstance(requireContext())

//        val searchSurah = view.findViewById<EditText>(R.id.searchSurah_bar)





        val textQariName = view.findViewById<TextView>(R.id.text_qariName)
        val backgroundQari = view.findViewById<ImageView>(R.id.background_qari)

        val myVib = requireContext().getSystemService(VIBRATOR_SERVICE) as Vibrator

        val animFadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
        val animFadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        val btnBookmarks = view.findViewById<MaterialCardView>(R.id.btn_gotoBookmark)

        btnBookmarks.setOnClickListener {
            val bookmarkFragment = BookmarkFragment().apply {  }
            bookmarkFragment.show(parentFragmentManager, "bookmark")

        }

//        animFadeOut.reset()
//        backgroundQari.clearAnimation()
//        backgroundQari.startAnimation(animFadeOut)
//        animFadeIn.reset()
//        backgroundQari.clearAnimation()
        val a = AnimationUtils.loadAnimation(requireContext(), R.anim.scale)
//        a.reset()
//        textQariName.clearAnimation()
//        textQariName.startAnimation(a)
        backgroundQari.setOnLongClickListener {
            myVib.vibrate(75)
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Qari Selection")
                .setSingleChoiceItems(arrayOf(
                    "Mishary Rasheed Al-Afasy",
                    "Maheer Al-Muaiqly",
                    "Sa'ad Al-Ghamidi",
                    "Abdurrahman As-Sudais",
                    "Yasser Ad-Dussary",
                    "Abdullah Ali Jaber",
                    "Ahmed Ibn Ali Al-Ajamy",
                    "Akram Al-Alaqim",
                    "Ali Hajjaj Al-Souasi",
                    "Ayman Sowaid",
                    "Abdul Basit Mujawwad",
                    "Abdullah Awaad Al-Juwaany",
                    "Abdullah Basfar",
                    "Abu Bakr Ash-Shatri",
                    "Abdullah Matroud",
                    "Ahmed Neana"
                ),
                    SharedPreferences(requireContext()).isQari) {dialog,position ->
                    when (position) {
                        0 -> {
                            SharedPreferences(requireContext()).isQari = 0
                            textQariName.text = "Mishary Rasheed \nAlafasy"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://i1.sndcdn.com/artworks-000133971668-pj9ovr-t500x500.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }
                        1 -> {
                            SharedPreferences(requireContext()).isQari = 1
                            textQariName.text = "Maheer \nAl-Muaeqly"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://suryakepri.com/wp-content/uploads/2020/05/9c1db80db1b59b10933d5c7243106602e8364580.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }
                        2 -> {
                            SharedPreferences(requireContext()).isQari = 2
                            textQariName.text = "Saad \nAl-Ghamidi"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://i0.wp.com/www.arrahmah.id/images/stories/2021/07/Murottal-Per-Halaman-Al-Quran-Oleh-Syaikh-Al-Ghamidi-Download-Gratis.jpg?fit=960%2C960&ssl=1")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }
                        3 -> {
                            SharedPreferences(requireContext()).isQari = 3
                            textQariName.text = "Abdurrahman \nAs-Sudais"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://www.madaninews.id/wp-content/uploads/2018/07/Abdul-Rahman-Al-Sudais-at-digital-mode-by-syed-noman-zafar-855x1024.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }
                        4 -> {
                            SharedPreferences(requireContext()).isQari = 4
                            textQariName.text = "Yasser \nAd-Dussary"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("http://quran.com.kw/en/wp-content/uploads/yaser-aldousarui.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }
                        5 -> {
                            SharedPreferences(requireContext()).isQari = 5
                            textQariName.text = "Abdullah \nAli Jaber"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://lastfm.freetls.fastly.net/i/u/770x0/910e866b14c84fb7b8a78360d853303e.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        6 -> {
                            SharedPreferences(requireContext()).isQari = 6
                            textQariName.text = "Ahmed Ibn Ali \nAl-Ajamy"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReQlQJHGk84CaBAGzO5zBiahJyzV1tYkwaPkzOYWDjeJXo8OrxHHAFfVrGxcK6rbg1gKI&usqp=CAU")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        7 -> {
                            SharedPreferences(requireContext()).isQari = 7
                            textQariName.text = "Akram \nAl-Alaqimy"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://liveonlineradio.net/wp-content/uploads/2018/05/Akram-Alalaqmi.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        8 -> {
                            SharedPreferences(requireContext()).isQari = 8
                            textQariName.text = "Ali Hajjaj \nAl-Souasi"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://tvquran.com/uploads/authors/images/علي%20حجاج%20السويسي.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        9 -> {
                            SharedPreferences(requireContext()).isQari = 9
                            textQariName.text = "Ayman \nSowaid"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://1.bp.blogspot.com/-ZjTIngf6yG8/XroBp6BcdSI/AAAAAAAAbLA/d29pC5kwMZAfssdH2ZtD31yyzdyR99kKgCLcBGAsYHQ/s1600/20200512_084836.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        10 -> {
                            SharedPreferences(requireContext()).isQari = 10
                            textQariName.text = "Abdul Basit \nMujawwad"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://i1.sndcdn.com/artworks-bkyUL4bz8a9tMi1y-u70kmw-t500x500.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        11-> {
                            SharedPreferences(requireContext()).isQari = 11
                            textQariName.text = "Abdullah Awaad \nAl-Juwaany"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://i.ytimg.com/vi/J52Nzy-fu7Y/sddefault.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        12 -> {
                            SharedPreferences(requireContext()).isQari = 12
                            textQariName.text = "Abdullah \nBasfar"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://i0.wp.com/suaraislam.id/wp-content/uploads/2020/09/abdullah-bashfar.jpg?fit=650%2C433&ssl=1")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        13 -> {
                            SharedPreferences(requireContext()).isQari = 13
                            textQariName.text = "Abu Bakr \nAsh-Shatri"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://www.muslimobsession.com/wp-content/uploads/2020/03/q.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        14 -> {
                            SharedPreferences(requireContext()).isQari = 14
                            textQariName.text = "Abdullah \nMatroud"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://i.ytimg.com/vi/1oL2Lty0Y7s/maxresdefault.jpg")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                        15 -> {
                            SharedPreferences(requireContext()).isQari = 15
                            textQariName.text = "Ahmed \nNeana"
                            backgroundQari.startAnimation(animFadeOut)
                            textQariName.startAnimation(a)
                            backgroundQari.load("https://i.ytimg.com/vi/scB6TU3U8nE/maxresdefault.jpg ")
                            backgroundQari.startAnimation(animFadeIn)
                            backgroundQari.alpha = 0.72f
                            myVib.vibrate(50)
                            dialog.dismiss()
                        }

                    }
                }
                .setNegativeButton("Cancel") {_,_ ->
                }
                .create()
                .show()
            true
        }

        when(SharedPreferences(requireContext()).isQari) {
            0 -> {
                textQariName.text = "Mishary Rasheed \nAlafasy"
                backgroundQari.load("https://i1.sndcdn.com/artworks-000133971668-pj9ovr-t500x500.jpg")
                backgroundQari.alpha = 0.72f
            }
            1 -> {
                textQariName.text = "Maheer \nAl-Muaeqly"
                backgroundQari.load("https://suryakepri.com/wp-content/uploads/2020/05/9c1db80db1b59b10933d5c7243106602e8364580.jpg")
                backgroundQari.alpha = 0.72f

            }
            2 -> {
                textQariName.text = "Saad \nAl-Ghamidi"
                backgroundQari.load("https://i0.wp.com/www.arrahmah.id/images/stories/2021/07/Murottal-Per-Halaman-Al-Quran-Oleh-Syaikh-Al-Ghamidi-Download-Gratis.jpg?fit=960%2C960&ssl=1")
                backgroundQari.alpha = 0.72f
            }
            3 -> {
                textQariName.text = "Abdurrahman \nAs Sudais"
                backgroundQari.load("https://www.madaninews.id/wp-content/uploads/2018/07/Abdul-Rahman-Al-Sudais-at-digital-mode-by-syed-noman-zafar-855x1024.jpg")
                backgroundQari.alpha = 0.72f
            }
            4 -> {
                textQariName.text = "Yasser \nAd-Dussary"
                backgroundQari.load("http://quran.com.kw/en/wp-content/uploads/yaser-aldousarui.jpg")
                backgroundQari.alpha = 0.72f
            }
            5 -> {
                textQariName.text = "Abdullah \nAli Jaber"
                backgroundQari.load("https://lastfm.freetls.fastly.net/i/u/770x0/910e866b14c84fb7b8a78360d853303e.jpg")
                backgroundQari.alpha = 0.72f
            }
            6 -> {
                textQariName.text = "Ahmed Ibn Ali \nAl-Ajamy"
                backgroundQari.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReQlQJHGk84CaBAGzO5zBiahJyzV1tYkwaPkzOYWDjeJXo8OrxHHAFfVrGxcK6rbg1gKI&usqp=CAU")
                backgroundQari.alpha = 0.72f
            }
            7 -> {
                textQariName.text = "Akram \nAl-Alaqimy"
                backgroundQari.load("https://liveonlineradio.net/wp-content/uploads/2018/05/Akram-Alalaqmi.jpg")
                backgroundQari.alpha = 0.72f
            }
            8 -> {
                textQariName.text = "Ali Hajjaj \nAl-Souasi"
                backgroundQari.load("https://tvquran.com/uploads/authors/images/علي%20حجاج%20السويسي.jpg")
                backgroundQari.alpha = 0.72f
            }
            9 -> {
                textQariName.text = "Ayman \nSowaid"
                backgroundQari.load("https://1.bp.blogspot.com/-ZjTIngf6yG8/XroBp6BcdSI/AAAAAAAAbLA/d29pC5kwMZAfssdH2ZtD31yyzdyR99kKgCLcBGAsYHQ/s1600/20200512_084836.jpg")
                backgroundQari.alpha = 0.72f
            }
            10 -> {
                textQariName.text = "Abdul Basit \nMujawwad"
                backgroundQari.load("https://i1.sndcdn.com/artworks-bkyUL4bz8a9tMi1y-u70kmw-t500x500.jpg")
                backgroundQari.alpha = 0.72f
            }
            11 -> {
                textQariName.text = "Abdullah Awaad \nAl-Juwaany"
                backgroundQari.load("https://i.ytimg.com/vi/J52Nzy-fu7Y/sddefault.jpg")
                backgroundQari.alpha = 0.72f
            }
            12 -> {
                textQariName.text = "Abdullah \nBasfar"
                backgroundQari.load("https://i0.wp.com/suaraislam.id/wp-content/uploads/2020/09/abdullah-bashfar.jpg?fit=650%2C433&ssl=1")
                backgroundQari.alpha = 0.72f
            }
            13 -> {
                textQariName.text = "Abu Bakr \nAsh-Shatri"
                backgroundQari.load("https://www.muslimobsession.com/wp-content/uploads/2020/03/q.jpg")
                backgroundQari.alpha = 0.72f
            }
            14 -> {
                textQariName.text = "Abdullah\n Matroud"
                backgroundQari.load("https://i.ytimg.com/vi/1oL2Lty0Y7s/maxresdefault.jpg")
                backgroundQari.alpha = 0.72f
            }
            15 -> {
                textQariName.text = "Ahmed \nNeana"
                backgroundQari.load("https://i.ytimg.com/vi/scB6TU3U8nE/maxresdefault.jpg ")
                backgroundQari.alpha = 0.72f
            }
        }





        lifecycleScope.launch{
            val surahList = database.QuranDao().getSurahList()
            setQuranAdapter(surahList)
        }


//        searchSurah.setOnEditorActionListener { textView, id, keyEvent ->
//            if (id == EditorInfo.IME_ACTION_DONE){
//                val query = "%${textView.text}%"
//                lifecycleScope.launch {
//                    val quranSearchList = database.QuranDao().getSurahSearch(query)
//                    setQuranAdapter(quranSearchList)
//
//                }
//
//            }
//            false
//        }








    }







    fun setQuranAdapter(quranSearchList: List<Surah>) {
        viewModel.settotalAyah(quranSearchList)
        lifecycleScope.launch {
            val adapter = SurahAdapter(quranSearchList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())



            adapter.itemClickListener = {
                val intent = Intent(requireContext(), QuranActivity::class.java)
                intent.putExtra("surah", it.surahNumber)
                intent.putExtra("surahName", it.surahName)
                startActivity(intent)
                getActivity()?.overridePendingTransition(R.anim.bottom_up, R.anim.nothing)




            }
        }
    }


}

