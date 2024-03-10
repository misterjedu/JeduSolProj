package tech.oladapo.mysolanaproject

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.oladapo.mysolanaproject.usecases.SimpleTransaction
import tech.oladapo.mysolanaproject.util.explorerUrl
import tech.oladapo.mysolanaproject.util.generateHotAccountFromSeedPhrase
import tech.oladapo.mysolanaproject.util.seedphraseOne

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val simpleTransaction: SimpleTransaction,
) : ViewModel() {

    var greet = "Hello Android viewmodel"
    fun performTransaction() {

        val account1 = generateHotAccountFromSeedPhrase(seedphraseOne)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val signature: String = simpleTransaction.createComplexAccount(account1).getOrElse {
                    throw it
                }
                println("Sol log: Transaction Successful")
                println(explorerUrl(signature))
            } catch (e: Exception) {
                //Do something here
                println("Sol log: An error occurred")
            }
        }
    }
}