package com.hamas.app_project_suhuf

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import hamas.app_project_suhuf.R

class FragmentSetAppearance : Fragment(R.layout.activity_fragment_set_appearance) {
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        // Back Button
        val imgBack = view.findViewById<ImageView>(R.id.img_backappearance)
        imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // Setting Dark Mode
        val switchDark = view.findViewById<SwitchMaterial>(R.id.switch_nightmode)
        switchDark.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {
                SharedPreferences(requireContext()).theme = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                SharedPreferences(requireContext()).theme = false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        when(SharedPreferences(requireContext()).theme) {
            false -> switchDark.isChecked = false
            true -> switchDark.isChecked = true
        }




        // Setting Arabic Font Size
        val sliderArabic = view.findViewById<Slider>(R.id.sliderSettings_ARSize)
        val previewArabic = view.findViewById<TextView>(R.id.preview_arabic)
        sliderArabic.addOnChangeListener { slider, size, fromUser ->
            SharedPreferences(requireContext()).isArabSize = size.toInt()
            previewArabic.textSize = size
        }
        sliderArabic.value = SharedPreferences(requireContext()).isArabSize.toFloat()

        // Setting Translation Font Size
        val sliderTranslation = view.findViewById<Slider>(R.id.sliderSettings_ENSize)
        val previewTranslation = view.findViewById<TextView>(R.id.preview_en)
        val cardTranslation = view.findViewById<MaterialCardView>(R.id.card_setTranslation)
        sliderTranslation.addOnChangeListener { slider, size, fromUser ->
            if(SharedPreferences(requireContext()).isTextVisible == 0) {
                SharedPreferences(requireContext()).isTranslationSize = size.toInt()
                previewTranslation.textSize = size
            } else {
                cardTranslation.setOnClickListener {
                    Toast.makeText(requireContext(),"Focus mode is enabled Please disable, Settings > General > Focus Mode", Toast.LENGTH_SHORT).show()
                }
                sliderTranslation.isEnabled = false
            }
        }
        sliderTranslation.value = SharedPreferences(requireContext()).isTranslationSize.toFloat()

    }
}