package com.hamas.app_project_suhuf

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import hamas.app_project_suhuf.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BottomSheetSettings : BottomSheetDialogFragment() {

    var onDismissCallback: (() -> Unit)? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
    View? = inflater.inflate(R.layout.fragment_bottom_sheet_settings, container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_settings)
        val ayahNumber = SharedPreferences(requireContext()).isLRAyahNumber
        val surahNumber = SharedPreferences(requireContext()).IsLRSurahNumber
        val myVib = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.btn_dismissSetting) {
                dismiss()
            }
            true
        }

        // Setting: Translation Size Slider
        val sliderTranslation = view.findViewById<Slider>(R.id.sliderQuickSettings_ENSize)
        val cardTranslation = view.findViewById<MaterialCardView>(R.id.cardQuickSettings_setTranslation)
        sliderTranslation.addOnChangeListener { slider, size, fromUser ->
            if (SharedPreferences(requireContext()).isTextVisible == 0) {
                SharedPreferences(requireContext()).isTranslationSize = size.toInt()
                onDismissCallback?.invoke()
            } else {
                sliderTranslation.isEnabled = false
                cardTranslation.setOnClickListener {
                    Toast.makeText(requireContext(),"Focus mode is enabled, Please disable", Toast.LENGTH_SHORT).show()
                }
            }
        }
        sliderTranslation.value = SharedPreferences(requireContext()).isTranslationSize.toFloat()


        // Setting: Focus Switch
        val focusSwitch = view.findViewById<SwitchMaterial>(R.id.switch_focusQS)
        focusSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                SharedPreferences(requireContext()).isTextVisible = 1
                QuranAdapter.QuranViewHolder(view).textTranslation?.isVisible = false
                cardTranslation.isClickable = true

                onDismissCallback?.invoke()
            } else {
                SharedPreferences(requireContext()).isTextVisible = 0
                QuranAdapter.QuranViewHolder(view).textTranslation?.isVisible = true
                cardTranslation.isClickable = false
                sliderTranslation.isEnabled = true

                onDismissCallback?.invoke()
            }


            lifecycleScope.launch {
                myVib.vibrate(35)
                delay(30)
                myVib.vibrate(35)
            }
        }
        when(SharedPreferences(requireContext()).isTextVisible) {
            0 -> focusSwitch.isChecked = false
            1 -> focusSwitch.isChecked = true
        }

        // Setting: Tajweed Switch
        val tajweedSwitch = view.findViewById<SwitchMaterial>(R.id.switch_tajweedQS)
        tajweedSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {
                SharedPreferences(requireContext()).isTajweedColorized = 1
                onDismissCallback?.invoke()
            } else {
                SharedPreferences(requireContext()).isTajweedColorized = 0
                onDismissCallback?.invoke()
            }
            lifecycleScope.launch {
                myVib.vibrate(35)
                delay(30)
                myVib.vibrate(35)
            }
        }
        when(SharedPreferences(requireContext()).isTajweedColorized) {
            0 -> tajweedSwitch.isChecked = false
            1 -> tajweedSwitch.isChecked = true
        }

        // Setting: Arabic Size Slider
        val sliderArabic = view.findViewById<Slider>(R.id.sliderQuickSettings_ARSize)
        sliderArabic.addOnChangeListener { slider, size, fromUser ->
            SharedPreferences(requireContext()).isArabSize = size.toInt()
            onDismissCallback?.invoke()
        }
        sliderArabic.value = SharedPreferences(requireContext()).isArabSize.toFloat()








    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissCallback?.invoke()
    }

}