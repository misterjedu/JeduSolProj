package tech.oladapo.mysolanaproject.networking

import com.solana.Solana

interface SolanaNetworkApi {
    fun getNetWork(): Solana
}