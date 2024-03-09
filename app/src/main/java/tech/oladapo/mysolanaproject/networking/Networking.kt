package tech.oladapo.mysolanaproject.networking

import com.solana.Solana
import com.solana.actions.sendSOL
import com.solana.api.AccountInfoSerializer
import com.solana.api.getAccountInfo
import com.solana.api.getBlock
import com.solana.core.HotAccount
import com.solana.core.PublicKey
import com.solana.models.buffer.AccountInfoData
import com.solana.networking.HttpNetworkingRouter
import com.solana.networking.RPCEndpoint
import com.solana.networking.serialization.serializers.base64.BorshAsBase64JsonArraySerializer

class Networking {
    suspend fun dd() {

       val hotAccount = HotAccount()

        hotAccount.publicKey

        val endPoint = RPCEndpoint.devnetSolana
        val network = HttpNetworkingRouter(endPoint)
        val solana = Solana(network)

//        KeyPair

        solana.api.getBlock(3)

//        solana.action.

        val serializer = AccountInfoSerializer(BorshAsBase64JsonArraySerializer((AccountInfoData.serializer())))
        val account = solana.api.getAccountInfo(serializer, PublicKey("8hoBQbSFKfDK3Mo7Wwc15Pp2bbkYuJE8TdQmnHNDjXoQ")).getOrThrow()

    }
}

