package tech.oladapo.mysolanaproject.usecases

import com.solana.core.HotAccount

import javax.inject.Inject
import tech.oladapo.mysolanaproject.networking.SolanaNetworkApi
import tech.oladapo.mysolanaproject.util.generateHotAccountFromSeedPhrase
import tech.oladapo.mysolanaproject.util.seedPhraseTwo

class CreateTokenUseCase @Inject constructor(
    private val api: SolanaNetworkApi
) {

    /**
     * Instructions...
     * Create a new account
     * Initialize that account as a mint
     * Create an associated token account
     * Mint new tokens to that associated token account
     */
    fun mintProgram() {
//        api.getNetWork().action.
    }

    suspend fun createComplexAccount(account: HotAccount): Result<String> {

        val mintWallet = HotAccount()

        val account2 = generateHotAccountFromSeedPhrase(seedPhraseTwo)



        return Result.success("")
    }


}