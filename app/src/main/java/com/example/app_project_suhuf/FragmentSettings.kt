package com.example.app_project_suhuf


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.app_quran.data.QuranDataBase
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.launch

class FragmentSettings: Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val focusSwitch = view.findViewById<SwitchMaterial>(R.id.switch_focus)
        val database = QuranDataBase.getInstance(requireContext())
        val nightswitch = view.findViewById<SwitchMaterial>(R.id.switch_night)


//            focusSwitch.setOnCheckedChangeListener{buttonView, isChecked ->
//                if (data) {
//                    button.setvisibility(View.VISIBLE);
//                }else {
//                    button.setvisibility(View.INVISIBLE);
//                }
//            }


        nightswitch.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

            }
    }

    }
}