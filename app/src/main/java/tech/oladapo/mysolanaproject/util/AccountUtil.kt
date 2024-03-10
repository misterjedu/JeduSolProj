package tech.oladapo.mysolanaproject.util

import androidx.core.net.toUri
import com.solana.core.DerivationPath
import com.solana.core.HotAccount
import com.solana.vendor.bip39.Mnemonic
import com.solana.vendor.bip39.WordCount
import java.net.URL
import java.util.Arrays

fun generateNewSeedPhrase() =
    Mnemonic(WordCount.COUNT_24).phrase

fun generateHotAccountFromSeedPhrase(seedPhrase: List<String>) =
    HotAccount.fromMnemonic(seedPhrase, "", DerivationPath.BIP44_M_44H_501H_0H)

fun explorerUrl(sig: String): URL = URL("https://explorer.solana.com/tx/$sig")
