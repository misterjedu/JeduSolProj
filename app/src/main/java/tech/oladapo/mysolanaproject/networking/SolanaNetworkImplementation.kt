package tech.oladapo.mysolanaproject.networking

import com.solana.Solana
import com.solana.networking.HttpNetworkingRouter
import com.solana.networking.RPCEndpoint

class SolanaNetworkImplementation : SolanaNetworkApi {
    override fun getNetWork(): Solana {
        val endPoint = RPCEndpoint.devnetSolana
        val network = HttpNetworkingRouter(endPoint)
        return Solana(network)
    }
}

