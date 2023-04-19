package com.hamas.app_project_suhuf

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.hamas.app_project_suhuf.database.*
import com.hamas.app_quran.data.QuranDataBase
import com.l4digital.fastscroll.FastScrollRecyclerView
import hamas.app_project_suhuf.R
import kotlinx.coroutines.launch
import snow.player.PlayMode
import snow.player.PlayerClient
import snow.player.audio.MusicItem
import snow.player.playlist.Playlist


class QuranActivity : AppCompatActivity(R.layout.activity_quran) {





    private val viewModel: SurahViewmodel by viewModels()
    fun copyTextToClipboard(quran: quran_database) {
        val textToCopy =
            if (SharedPreferences(this@QuranActivity).isTextVisible == 0) {
                ("${quran.textAyah} \n\n${quran.translation} \n(QS ${quran.surahName}:${quran.noAyah}) ")
            } else {
                ("${quran.textAyah} \n(QS ${quran.surahName}:${quran.noAyah}) ")
            }


        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(this, "Ayat ${quran.noAyah} copied to Clipboard! ", Toast.LENGTH_LONG)
            .show()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        val myVib = this.getSystemService(VIBRATOR_SERVICE) as Vibrator

        when(SharedPreferences(this@QuranActivity).theme) {
            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        val btnBack = findViewById<ImageView>(R.id.backbutton_surah3)
        val textTop = findViewById<TextView>(R.id.text_surah_name1)
        val recyclerView = findViewById<FastScrollRecyclerView>(R.id.recyclerview_Quran)
        val database = QuranDataBase.getInstance(this)
        val surahNumber: Int = intent.getIntExtra(QuranActivity.SURAH_NUMBER, 1)
        val gotoSearchBar = findViewById<ImageButton>(R.id.btn_gotoSearch)
        val gotoQuickSettings = findViewById<ImageButton>(R.id.goto_quickSettings)











        gotoSearchBar.setOnClickListener {
            val intent = Intent(this@QuranActivity, SearchActivity2::class.java)
            startActivity(intent)
        }


        btnBack.setOnClickListener {
            super.onBackPressed()
            finish()
            overridePendingTransition(R.anim.nothing, R.anim.bottom_down)

        }

        val btnNextSurah: ImageView = findViewById(R.id.btn_nextSurah)
        val btnPreviousSurah: ImageView = findViewById(R.id.btn_previousSurah)

        lifecycleScope.launch {
            val quran = database.QuranDao().getAyahFromSurah(surahNumber)
            btnNextSurah.setOnClickListener {

                val intent = Intent(this@QuranActivity, QuranActivity::class.java)
                intent.putExtra("surah", quran[0].numberSurah?.plus(1) ?: true)
                SharedPreferences(this@QuranActivity).IsLRSurahNumber = quran[0].numberSurah!!
                SharedPreferences(this@QuranActivity).isLRAyahNumber = quran[0].noAyah!!
                myVib.vibrate(75)
                startActivity(intent)
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.nothing)
            }
            btnPreviousSurah.setOnClickListener {


                val intent = Intent(this@QuranActivity, QuranActivity::class.java)
                intent.putExtra("surah", quran[0].numberSurah?.minus(1) ?: true)
                SharedPreferences(this@QuranActivity).IsLRSurahNumber = quran[0].numberSurah!!.minus(1)
                SharedPreferences(this@QuranActivity).isLRAyahNumber = quran[0].noAyah!!
                myVib.vibrate(75)
                startActivity(intent)
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.nothing)


            }

            if (quran[0].numberSurah!! <= 1) {
                btnPreviousSurah.isGone = true
//                val intent = Intent(this@QuranActivity, QuranActivity::class.java)
//                intent.putExtra("surah", quran[0].numberSurah == 114)
//                startActivity(intent)
//                finish()
            }
            if (quran[0].numberSurah!! == 114) {
                btnNextSurah.isGone = true
            }

            textTop.text = "${quran[0].surahName}"
        }


            val buttonplaySurah = findViewById<ImageView>(R.id.btn_play_surah)


            val playerClient =
                PlayerClient.newInstance(this@QuranActivity, MyPlayerService::class.java)
            val playerClient2 =
                PlayerClient.newInstance(this@QuranActivity, MyPlayerService::class.java)

            playerClient.connect { success ->
                playerClient.playMode = PlayMode.SINGLE_ONCE
                Log.d("App", "connect: $success")

            }

            fun createPlaylist(quranList: List<quran_database>): Playlist {
                val listAyahSound = mutableListOf<MusicItem>()
                quranList.forEach {
                    val qariString = when (SharedPreferences(this@QuranActivity).isQari) {
                        0 -> {
                            "Alafasy_128kbps"
                        }
                        1 -> {
                            "MaherAlMuaiqly128kbps"
                        }
                        2 -> {
                            "Ghamidi_40kbps"
                        }
                        3 -> {
                            "Abdurrahmaan_As-Sudais_192kbps"
                        }
                        4 -> {
                            "Yasser_Ad-Dussary_128kbps"
                        }
                        5 -> {
                            "Ali_Jaber_64kbps"
                        }
                        6 -> {
                            "Ahmed Ibn Ali Al-Ajamy"
                        }
                        7 -> {
                            "Akram Al-Alaqimy"
                        }
                        8 -> {
                            "Ali Hajjaj Al-Souasi"
                        }
                        9 -> {
                            "Ayman Sowaid"
                        }
                        10 -> {
                            "Abdul Basit Mujawwad"
                        }
                        11 -> {
                            "Abdullah Awaad Al-Juwaany"
                        }
                        12 -> {
                            "Abdullah Basfar"
                        }
                        13 -> {
                            "Abu Bakr Ash-Shatri"
                        }
                        14 -> {
                            "Abdullah Matroud"
                        }
                        15 -> {
                            "Ahmed Neana"
                        }
                        else -> {}
                    }
                    val qariNameString = when (SharedPreferences(this@QuranActivity).isQari) {
                        0 -> {
                            "Mishary Rasheed Al-Afasy"
                        }
                        1 -> {
                            "Maheer Al-Muaiqly"
                        }
                        2 -> {
                            "Sa'ad Al-Ghamidi"
                        }
                        3 -> {
                            "Abdurrahman As-Sudais"
                        }
                        4 -> {
                            "Yasser Ad-Dussary"
                        }
                        5 -> {
                            "Abdullah Ali Jaber"
                        }
                        6 -> {
                            "Ahmed_ibn_Ali_al-Ajamy_128kbps_ketaballah.net"
                        }
                        7 -> {
                            "Akram_AlAlaqimy_128kbps"
                        }
                        8 -> {
                            "Ali_Hajjaj_AlSuesy_128kbps"
                        }
                        9 -> {
                            "Ayman_Sowaid_64kbps/001002.mp3"
                        }
                        10 -> {
                            "Abdul_Basit_Mujawwad_128kbps"
                        }
                        11 -> {
                            "Abdullaah_3awwaad_Al-Juhaynee_128kbps"
                        }
                        12 -> {
                            "Abdullah_Basfar_192kbps"
                        }
                        13 -> {
                            "Abu_Bakr_Ash-Shaatree_128kbps"
                        }
                        14 -> {
                            "Abdullah_Matroud_128kbps"
                        }
                        15 -> {
                            "Ahmed_Neana_128kbps"
                        }
                        else -> {}
                    }

                    val qariImgString = when (SharedPreferences(this@QuranActivity).isQari) {
                        0 -> {
                            "https://i1.sndcdn.com/artworks-000133971668-pj9ovr-t500x500.jpg"
                        }
                        1 -> {
                            "https://suryakepri.com/wp-content/uploads/2020/05/9c1db80db1b59b10933d5c7243106602e8364580.jpg"
                        }
                        2 -> {
                            "https://i0.wp.com/www.arrahmah.id/images/stories/2021/07/Murottal-Per-Halaman-Al-Quran-Oleh-Syaikh-Al-Ghamidi-Download-Gratis.jpg?fit=960%2C960&ssl=1"
                        }
                        3 -> {
                            "https://www.madaninews.id/wp-content/uploads/2018/07/Abdul-Rahman-Al-Sudais-at-digital-mode-by-syed-noman-zafar-855x1024.jpg"
                        }
                        4 -> {
                            "http://quran.com.kw/en/wp-content/uploads/yaser-aldousarui.jpg"
                        }
                        5 -> {
                            "https://lastfm.freetls.fastly.net/i/u/770x0/910e866b14c84fb7b8a78360d853303e.jpg"
                        }
                        6 -> {
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReQlQJHGk84CaBAGzO5zBiahJyzV1tYkwaPkzOYWDjeJXo8OrxHHAFfVrGxcK6rbg1gKI&usqp=CAU"
                        }
                        7 -> {
                            "https://liveonlineradio.net/wp-content/uploads/2018/05/Akram-Alalaqmi.jpg"
                        }
                        8 -> {
                            "https://tvquran.com/uploads/authors/images/علي%20حجاج%20السويسي.jpg"
                        }
                        9 -> {
                            "https://1.bp.blogspot.com/-ZjTIngf6yG8/XroBp6BcdSI/AAAAAAAAbLA/d29pC5kwMZAfssdH2ZtD31yyzdyR99kKgCLcBGAsYHQ/s1600/20200512_084836.jpg"
                        }
                        10 -> {
                            "https://i1.sndcdn.com/artworks-bkyUL4bz8a9tMi1y-u70kmw-t500x500.jpg"
                        }
                        11 -> {
                            "https://i.ytimg.com/vi/J52Nzy-fu7Y/sddefault.jpg"
                        }
                        12 -> {
                            "https://i0.wp.com/suaraislam.id/wp-content/uploads/2020/09/abdullah-bashfar.jpg?fit=650%2C433&ssl=1"
                        }
                        13 -> {
                            "https://www.muslimobsession.com/wp-content/uploads/2020/03/q.jpg"
                        }
                        14 -> {
                            "https://i.ytimg.com/vi/1oL2Lty0Y7s/maxresdefault.jpg"
                        }
                        15 -> {
                            "https://i.ytimg.com/vi/scB6TU3U8nE/maxresdefault.jpg"
                        }
                        else -> {}
                    }
                    val formattedAyahNumber = String.format("%03d", it.noAyah)
                    val formattedSurahNumber = String.format("%03d", it.numberSurah)
                    val gambarMaheer = "$qariImgString"
                    val murattalItem = MusicItem.Builder()
                        .setIconUri(gambarMaheer)
                        .setTitle("${it.surahName}: ${it.numberSurah}")
                        .setArtist("$qariNameString")
                        .setAlbum("Quran Recitations")
                        .setUri("https://everyayah.com/data/$qariString/$formattedSurahNumber$formattedAyahNumber.mp3")
                        .autoDuration()
                        .build()

                    listAyahSound.add(murattalItem)

                }

                val playlist: Playlist = Playlist.Builder()
                    .appendAll(listAyahSound)
                    .build()
                playerClient.setPlaylist(playlist, true)
                return Playlist.Builder()
                    .appendAll(listAyahSound)
                    .build()
            }



            fun setQuranAdapter(quranList: List<quran_database>) {
                lifecycleScope.launch {
                    val adapter = QuranAdapter(quranList)

                    recyclerView.adapter = adapter




                    gotoQuickSettings.setOnClickListener {
                            val bottomsheetSettings = BottomSheetSettings().apply {}
                            bottomsheetSettings.show(supportFragmentManager, "settings")
                            bottomsheetSettings.onDismissCallback = {
                                adapter.notifyDataSetChanged()
                        }
                    }



                    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView,
                            newState: Int
                        ) {
                            super.onScrollStateChanged(recyclerView, newState)
                            when (newState) {
                                RecyclerView.SCROLL_STATE_IDLE -> {
                                    lifecycleScope.launch {
                                        val currentPosition = recyclerView.getCurrentPosition()
                                        val lastQuranRead = quranList.getOrNull(currentPosition)
                                        lastQuranRead?.let {
                                            val totalAyah = database.QuranDao().getTotalAyah(it.numberSurah!!)

                                            Log.d("TOTALAYAT", totalAyah.toString())
//
                                            SharedPreferences(this@QuranActivity).IsLRSurahNumber =
                                                it.numberSurah!!
                                            SharedPreferences(this@QuranActivity).isLRSurahNama =
                                                it.surahName
                                            SharedPreferences(this@QuranActivity).isLRAyahNumber =
                                                it.noAyah!!
                                            SharedPreferences(this@QuranActivity).isLRCurrentTotalAyah =
                                                totalAyah[0].totalAyah

//                                            Toast.makeText(this@QuranActivity,
//                                                "${SharedPreferences(this@QuranActivity).isLRSurahNama.toString()} Ayat ${SharedPreferences(this@QuranActivity).isLRAyahNumber}",
//                                                Toast.LENGTH_SHORT).show()









                                        }

                                    }
                                }
                                RecyclerView.SCROLL_STATE_DRAGGING -> {


                                }
                                RecyclerView.SCROLL_STATE_SETTLING -> {

                                }
                            }

                        }
                    })



                    val lastAyahNumber = intent.getIntExtra(AYAH_NUMBER, 1)
                    lastAyahNumber?.let {
                        recyclerView.scrollToPosition(it - 1)
                    }







                    adapter.bookmarkClickListener = {
                        lifecycleScope.launch {
                            val bookmark = Bookmark(
                                it.id,
                                it.surahName,
                                it.noAyah,
                                it.numberSurah,
                                it.noAyah,
                                it.textAyah
                            )
                            val quranDao =
                                BookmarkDatabase.getInstance(this@QuranActivity).QuranDao()
                            quranDao.insertBookmark(bookmark)
                            Toast.makeText(
                                this@QuranActivity,
                                "Bookmark Added",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }


                    val quran = database.QuranDao().getAyahFromSurah(surahNumber)

                    playerClient2.addOnPlayingMusicItemChangeListener { _, position, _ ->


                        recyclerView.smoothScrollToPosition(position)





                        if (position == quranList.lastIndex) {
                            playerClient.playMode = PlayMode.SINGLE_ONCE
                            val intent = Intent(this@QuranActivity, QuranActivity::class.java)
                            intent.putExtra("surah", quran[0].numberSurah?.plus(1) ?: true)
                            SharedPreferences(this@QuranActivity).IsLRSurahNumber =
                                quran[0].numberSurah!!
                            SharedPreferences(this@QuranActivity).isLRAyahNumber =
                                quran[0].noAyah!!
                            startActivity(intent)
                            finish()
                        }
                    }



                    buttonplaySurah.setOnClickListener {

                        val playlist = createPlaylist(quranList)
                        playerClient2.connect {
                            playerClient2.setPlaylist(playlist, true)
                            playerClient2.playMode = PlayMode.PLAYLIST_LOOP

                        }

                        Toast.makeText(
                            this@QuranActivity,
                            "Playing Surah ${quran[0].surahName}",
                            Toast.LENGTH_SHORT
                        ).show()


                    }

                    adapter.footnotesClickListener = {
                        val footNotesFragment = FootNotesFragment().apply {
                            arguments = bundleOf(
                                FootNotesFragment.EXTRA_FOOTNOTES to it.footnotes,
                                FootNotesFragment.EXTRA_SURAH_NAME_AR to it.surahNameAr,
                                FootNotesFragment.EXTRA_SURAH_NAME to it.surahName,
                                FootNotesFragment.EXTRA_AYAH_NUMBER to it.noAyah


                            )
                        }
                        footNotesFragment.show(supportFragmentManager, "footnotes")
                    }

                    adapter.playClickListener = {
                        val qariString = when (SharedPreferences(this@QuranActivity).isQari) {
                            0 -> {
                                "Alafasy_128kbps"
                            }
                            1 -> {
                                "MaherAlMuaiqly128kbps"
                            }
                            2 -> {
                                "Ghamidi_40kbps"
                            }
                            3 -> {
                                "Abdurrahmaan_As-Sudais_192kbps"
                            }
                            4 -> {
                                "Yasser_Ad-Dussary_128kbps"
                            }
                            5 -> {
                                "Ali_Jaber_64kbps"
                            }
                            6 -> {
                                "Ahmed Ibn Ali Al-Ajamy"
                            }
                            7 -> {
                                "Akram Al-Alaqim"
                            }
                            8 -> {
                                "Ali Hajjaj Al-Souasi"
                            }
                            9 -> {
                                "Ayman Sowaid"
                            }
                            10 -> {
                                "Abdul Basit Mujawwad"
                            }
                            11 -> {
                                "Abdullah Awaad Al-Juwaany"
                            }
                            12 -> {
                                "Abdullah Basfar"
                            }
                            13 -> {
                                "Abu Bakr Ash-Shatri"
                            }
                            14 -> {
                                "Abdullah Matroud"
                            }
                            15 -> {
                                "Ahmed Neana"
                            }

                            else -> {}
                        }

                        val qariNameString =
                            when (SharedPreferences(this@QuranActivity).isQari) {
                                0 -> {
                                    "Alafasy_128kbps"
                                }
                                1 -> {
                                    "MaherAlMuaiqly128kbps"
                                }
                                2 -> {
                                    "Ghamidi_40kbps"
                                }
                                3 -> {
                                    "Abdurrahmaan_As-Sudais_192kbps"
                                }
                                4 -> {
                                    "Yasser_Ad-Dussary_128kbps"
                                }
                                5 -> {
                                    "Ali_Jaber_64kbps"
                                }
                                6 -> {
                                    "Ahmed Ibn Ali Al-Ajamy"
                                }
                                7 -> {
                                    "Akram Al-Alaqimy"
                                }
                                8 -> {
                                    "Ali Hajjaj Al-Souasi"
                                }
                                9 -> {
                                    "Ayman Sowaid"
                                }
                                10 -> {
                                    "Abdul Basit Mujawwad"
                                }
                                11 -> {
                                    "Abdullah Awaad Al-Juwaany"
                                }
                                12 -> {
                                    "Abdullah Basfar"
                                }
                                13 -> {
                                    "Abu Bakr Ash-Shatri"
                                }
                                14 -> {
                                    "Abdullah Matroud"
                                }
                                15 -> {
                                    "Ahmed Neana"
                                }
                                else -> {}
                            }

                        val qariImgString =
                            when (SharedPreferences(this@QuranActivity).isQari) {
                                0 -> {
                                    "https://i1.sndcdn.com/artworks-000133971668-pj9ovr-t500x500.jpg"
                                }
                                1 -> {
                                    "https://suryakepri.com/wp-content/uploads/2020/05/9c1db80db1b59b10933d5c7243106602e8364580.jpg"
                                }
                                2 -> {
                                    "https://i0.wp.com/www.arrahmah.id/images/stories/2021/07/Murottal-Per-Halaman-Al-Quran-Oleh-Syaikh-Al-Ghamidi-Download-Gratis.jpg?fit=960%2C960&ssl=1"
                                }
                                3 -> {
                                    "https://www.madaninews.id/wp-content/uploads/2018/07/Abdul-Rahman-Al-Sudais-at-digital-mode-by-syed-noman-zafar-855x1024.jpg"
                                }
                                4 -> {
                                    "http://quran.com.kw/en/wp-content/uploads/yaser-aldousarui.jpg"
                                }
                                5 -> {
                                    "https://lastfm.freetls.fastly.net/i/u/770x0/910e866b14c84fb7b8a78360d853303e.jpg"
                                }
                                6 -> {
                                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReQlQJHGk84CaBAGzO5zBiahJyzV1tYkwaPkzOYWDjeJXo8OrxHHAFfVrGxcK6rbg1gKI&usqp=CAU"
                                }
                                7 -> {
                                    "https://liveonlineradio.net/wp-content/uploads/2018/05/Akram-Alalaqmi.jpg"
                                }
                                8 -> {
                                    "https://tvquran.com/uploads/authors/images/علي%20حجاج%20السويسي.jpg"
                                }
                                9 -> {
                                    "https://1.bp.blogspot.com/-ZjTIngf6yG8/XroBp6BcdSI/AAAAAAAAbLA/d29pC5kwMZAfssdH2ZtD31yyzdyR99kKgCLcBGAsYHQ/s1600/20200512_084836.jpg"
                                }
                                10 -> {
                                    "https://i1.sndcdn.com/artworks-bkyUL4bz8a9tMi1y-u70kmw-t500x500.jpg"
                                }
                                11 -> {
                                    "https://i.ytimg.com/vi/J52Nzy-fu7Y/sddefault.jpg"
                                }
                                12 -> {
                                    "https://i0.wp.com/suaraislam.id/wp-content/uploads/2020/09/abdullah-bashfar.jpg?fit=650%2C433&ssl=1"
                                }
                                13 -> {
                                    "https://www.muslimobsession.com/wp-content/uploads/2020/03/q.jpg"
                                }
                                14 -> {
                                    "https://i.ytimg.com/vi/1oL2Lty0Y7s/maxresdefault.jpg"
                                }
                                15 -> {
                                    "https://i.ytimg.com/vi/scB6TU3U8nE/maxresdefault.jpg"
                                }

                                else -> {}
                            }
                        val gambarMaheer = "$qariImgString"
                        val audioUrl = String.format(
                            "https://everyayah.com/data/$qariString/%03d%03d.mp3",
                            it.numberSurah,
                            it.noAyah
                        )
                        val audio = MusicItem.Builder()
                            .setTitle("${it.surahName}: ${it.numberSurah}")
                            .setArtist("$qariNameString")
                            .setAlbum("Quran Recitations")
                            .autoDuration()
                            .setUri(audioUrl)
                            .setIconUri(gambarMaheer)
                            .build()
                        val playlist: Playlist = Playlist.Builder()
                            .append(audio)
                            .build()
                        playerClient.setPlaylist(playlist, true)
                        Toast.makeText(
                            this@QuranActivity,
                            "Playing ${it.surahName}: ${it.noAyah}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    adapter.menuClickListener = {
                        //TODO: MENU POP UP (Focus Mode [Remove Translation], Dark Mode)
                    }

                    adapter.itemClickListener = {

                    }

                    adapter.shareClickListener = {
                        val shareIntent = Intent()
                        shareIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            "${it.textAyah} \n\n${it.translation} \n(QS ${it.surahName}:${it.noAyah}) "
                        )
                        shareIntent.action = Intent.ACTION_SEND
                        shareIntent.type = "text/plain"
                        startActivity(Intent.createChooser(shareIntent, "Share To"))
                    }

                    adapter.copyClickListener = {
                        copyTextToClipboard(it)
                    }
                }

            }


            lifecycleScope.launch {

                val quranList = database.QuranDao().getAyahFromSurah(surahNumber)
                setQuranAdapter(quranList)
            }


        }
    companion object {
        const val SURAH_NUMBER = "surah"
        const val AYAH_NUMBER = "ayah"
        const val SURAH_NAME = "surah_name"
    }
    }



    fun RecyclerView.getCurrentPosition(): Int {
        return (this.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }









