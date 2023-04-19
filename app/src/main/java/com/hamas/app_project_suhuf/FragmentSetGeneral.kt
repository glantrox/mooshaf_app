package com.hamas.app_project_suhuf

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.google.android.material.switchmaterial.SwitchMaterial
import hamas.app_project_suhuf.R

class FragmentSetGeneral : Fragment(R.layout.activity_fragment_set_general) {
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
        val imgBack = view.findViewById<ImageView>(R.id.img_backgeneral)
        imgBack.setOnClickListener {
            findNavController().popBackStack()
        }



        // Setting Focus Mode
        val focusSwitch = view.findViewById<SwitchMaterial>(R.id.switch_focus)
        focusSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                SharedPreferences(requireContext()).isTextVisible = 1
            } else {
                SharedPreferences(requireContext()).isTextVisible = 0
            }
        }
        when(SharedPreferences(requireContext()).isTextVisible) {
            0 -> {
                focusSwitch.isChecked = false
            }
            1 -> {
                focusSwitch.isChecked = true

            }
        }

        // Setting Tajweed Mode
        val tajweedSwitch = view.findViewById<SwitchMaterial>(R.id.switch_tajweed)
        tajweedSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {
                SharedPreferences(requireContext()).isTajweedColorized = 1
            } else {
                SharedPreferences(requireContext()).isTajweedColorized = 0
            }
        }
        when(SharedPreferences(requireContext()).isTajweedColorized) {
            0 -> tajweedSwitch.isChecked = false
            1 -> tajweedSwitch.isChecked = true
        }



    }
}