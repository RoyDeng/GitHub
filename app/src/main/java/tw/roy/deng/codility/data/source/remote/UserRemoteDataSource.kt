package tw.roy.deng.codility.data.source.remote

import tw.roy.deng.codility.data.model.NetworkSearchResult
import tw.roy.deng.codility.utils.Result

interface UserRemoteDataSource {
    suspend fun searchUsers(q: String, page: Int): Result<NetworkSearchResult?>
}