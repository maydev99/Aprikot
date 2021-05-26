package com.bombadu.aprikot.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.bombadu.aprikot.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}