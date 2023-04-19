package com.hamas.app_project_suhuf


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import hamas.app_project_suhuf.R


class FragmentSettings: Fragment(R.layout.fragment_settings) {



    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getActivity()?.getWindow()?.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        when(SharedPreferences(requireContext()).theme) {
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

        // Navigate to Setting Appearance
        val gotoAppearance = view.findViewById<MaterialCardView>(R.id.card_gotoAppearance)
        gotoAppearance.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_settings_to_navigation_set_appearance2)
        }


       // Navigate to Setting General
        val gotoGeneral = view.findViewById<MaterialCardView>(R.id.card_gotoGeneral)
       gotoGeneral.setOnClickListener {
           findNavController().navigate(R.id.action_navigation_settings_to_navigation_set_general)


       }

        // Setting Qari Selection
        val setQari = view.findViewById<MaterialCardView>(R.id.setting_qari)
        val setQariText = view.findViewById<TextView>(R.id.text_settingQari)
        setQari.setOnClickListener {
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
                            setQariText.text = "Mishary Rasheed Al-Afasy"

                            dialog.dismiss()
                        }
                        1 -> {
                            SharedPreferences(requireContext()).isQari = 1
                            setQariText.text = "Maheer Al-Muaiqly"
                            dialog.dismiss()
                        }
                        2 -> {
                            SharedPreferences(requireContext()).isQari = 2
                            setQariText.text = "Sa'ad Al-Ghamidi"
                            dialog.dismiss()
                        }
                        3 -> {
                            SharedPreferences(requireContext()).isQari = 3
                            setQariText.text = "Abdurrahman As-Sudais"
                            dialog.dismiss()
                        }
                        4 -> {
                            SharedPreferences(requireContext()).isQari = 4
                            setQariText.text = "Yasser Ad-Dussary"
                            dialog.dismiss()
                        }
                        5 -> {
                            SharedPreferences(requireContext()).isQari = 5
                            setQariText.text = "Abdullah Ali Jaber"
                            dialog.dismiss()
                        }
                        6 -> {
                            SharedPreferences(requireContext()).isQari = 6
                            setQariText.text = "Ahmed Ibn Ali Al-Ajamy"
                            dialog.dismiss()
                        }
                        7 -> {
                            SharedPreferences(requireContext()).isQari = 7
                            setQariText.text = "Akram Al-Alaqim"
                            dialog.dismiss()
                        }
                        8 -> {
                            SharedPreferences(requireContext()).isQari = 8
                            setQariText.text = "Ali Hajjaj Al-Souasi"
                            dialog.dismiss()
                        }
                        9 -> {
                            SharedPreferences(requireContext()).isQari = 9
                            setQariText.text = "Ayman Sowaid"
                            dialog.dismiss()
                        }
                        10 -> {
                            SharedPreferences(requireContext()).isQari = 10
                            setQariText.text = "Abdul Basit Mujawwad"
                            dialog.dismiss()
                        }
                        11 -> {
                            SharedPreferences(requireContext()).isQari = 11
                            setQariText.text = "Abdullah Awaad Al-Juwaany"
                            dialog.dismiss()
                        }
                        12 -> {
                            SharedPreferences(requireContext()).isQari = 12
                            setQariText.text = "Abdullah Basfar"
                            dialog.dismiss()
                        }
                        13 -> {
                            SharedPreferences(requireContext()).isQari = 13
                            setQariText.text = "Abu Bakr Ash-Shatri"
                            dialog.dismiss()
                        }
                        14 -> {
                            SharedPreferences(requireContext()).isQari = 14
                            setQariText.text = "Abdullah Matroud"
                            dialog.dismiss()
                        }
                        15 -> {
                            SharedPreferences(requireContext()).isQari = 15
                            setQariText.text = "Ahmed Neana"
                            dialog.dismiss()
                        }
                    }
                }.setNegativeButton("Cancel") {_,_ ->

                }
                .create()
                .show()
        }
        when(SharedPreferences(requireContext()).isQari) {
            0 -> {
                setQariText.text = "Mishary Rasheed Al-Afasy"
            }
            1 -> {
                setQariText.text = "Maheer Al-Muaiqly"
            }
            2 -> {
                setQariText.text = "Sa'ad Al-Ghamidi"
            }
            3 -> {
                setQariText.text = "Abdurrahman As-Sudais"
            }
            4 -> {
                setQariText.text = "Yasser Ad-Dussary"
            }
            5 -> {
                setQariText.text = "Abdullah Ali Jaber"
            }
            6 -> {
                setQariText.text = "Ahmed Ibn Ali Al-Ajamy"
            }
            7 -> {
                setQariText.text = "Akram Al-Alaqim"
            }
            8 -> {
                setQariText.text = "Ali Hajjaj Al-Souasi"
            }
            9 -> {
                setQariText.text = "Ayman Sowaid"
            }
            10 -> {
                setQariText.text = "Abdul Basit Mujawwad"
            }
            11 -> {
                setQariText.text = "Abdullah Awaad Al-Juwaany"
            }
            12 -> {
                setQariText.text = "Abdullah Basfar"
            }
            13 -> {
                setQariText.text = "Abu Bakr Ash-Shatri"
            }
            14 -> {
                setQariText.text = "Abdullah Matroud"
            }
            15 -> {
                setQariText.text = "Ahmed Neana"
            }
        }

    }


}

