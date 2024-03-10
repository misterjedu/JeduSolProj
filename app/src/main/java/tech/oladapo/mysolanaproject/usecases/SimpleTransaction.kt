package tech.oladapo.mysolanaproject.usecases

import com.solana.api.getMinimumBalanceForRentExemption
import com.solana.api.getRecentBlockhash
import com.solana.api.sendTransaction
import com.solana.core.HotAccount
import com.solana.core.Transaction
import com.solana.programs.SystemProgram
import com.solana.programs.TokenProgram
import javax.inject.Inject
import tech.oladapo.mysolanaproject.networking.SolanaNetworkApi
import tech.oladapo.mysolanaproject.util.generateHotAccountFromSeedPhrase
import tech.oladapo.mysolanaproject.util.seedPhraseTwo
import tech.oladapo.mysolanaproject.util.seedphraseOne

class SimpleTransaction @Inject constructor(
    private val api: SolanaNetworkApi
) {

    suspend fun createSimpleAccount(account: HotAccount): Result<String> {

        // request the cost (in lamports) to allocate `space` number of bytes on chain
        val lamports: Long = api.getNetWork().api.getMinimumBalanceForRentExemption(0).getOrElse {
            return Result.failure(it)
        }

        val newAccount = HotAccount()

        val createAccountIx = SystemProgram.createAccount(
            fromPublicKey = account.publicKey,
            newAccountPublickey = newAccount.publicKey,
            lamports = lamports,
            space = 0,
            programId = SystemProgram.PROGRAM_ID,
        )

        val recentBlockHash = api.getNetWork().api.getRecentBlockhash().getOrElse {
            return Result.failure(it)
        }

        val transaction = Transaction().apply {
            // If Fee payer is not set, it's going to use the address of the first account that signs
            feePayer = account.publicKey
            setRecentBlockHash(recentBlockHash)
            addInstruction(createAccountIx)
            sign(account, newAccount)
        }

        val sig: String = api.getNetWork().api.sendTransaction(
            transaction,
            signers = listOf(account, newAccount),
            recentBlockHash = recentBlockHash,
        ).getOrElse { return Result.failure(it) }

        return Result.success(sig)
    }

    suspend fun createComplexAccount(account: HotAccount): Result<String> {

        // request the cost (in lamports) to allocate `space` number of bytes on chain
        val lamports: Long = api.getNetWork().api.getMinimumBalanceForRentExemption(0).getOrElse {
            return Result.failure(it)
        }

        val testWallet = HotAccount()

        val account2 = generateHotAccountFromSeedPhrase(seedPhraseTwo)

        val createAccountIx = SystemProgram.createAccount(
            fromPublicKey = account.publicKey,
            newAccountPublickey = testWallet.publicKey,
            lamports = lamports,
            space = 0,
            programId = SystemProgram.PROGRAM_ID,
        )

        val recentBlockHash = api.getNetWork().api.getRecentBlockhash().getOrElse {
            return Result.failure(it)
        }

        val transferToTestWalletIx = SystemProgram.transfer(
            lamports = lamports + 100_000,
            fromPublicKey = account.publicKey,
            toPublickKey = testWallet.publicKey,
        )

        val transferToWallet2 = SystemProgram.transfer(
            lamports = 100_000,
            fromPublicKey = account.publicKey,
            toPublickKey = account2.publicKey,
        )


        val transaction = Transaction().apply {
            // If Fee payer is not set, it's going to use the address of the first account that signs
            feePayer = account.publicKey
            setRecentBlockHash(recentBlockHash)
            addInstruction(createAccountIx, transferToTestWalletIx, transferToWallet2)
            sign(account, testWallet)
        }


        val sig: String = api.getNetWork().api.sendTransaction(
            transaction,
            signers = listOf(account, testWallet),
            recentBlockHash = recentBlockHash,
        ).getOrElse { return Result.failure(it) }

        return Result.success(sig)
    }
}