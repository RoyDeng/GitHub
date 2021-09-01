package tw.roy.deng.codility.data.source.repository

import tw.roy.deng.codility.data.model.SearchResult
import tw.roy.deng.codility.utils.Result

interface UserRepository {
    suspend fun searchUsers(page: Int): Result<SearchResult?>
}