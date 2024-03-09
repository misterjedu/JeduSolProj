package tech.oladapo.mysolanaproject

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var greet = "Hello Android viewmodel"

}