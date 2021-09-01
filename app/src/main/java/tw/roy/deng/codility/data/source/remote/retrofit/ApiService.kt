package tw.roy.deng.codility.data.source.remote.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tw.roy.deng.codility.data.model.NetworkSearchResult

interface ApiService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") q: String,
        @Query("page") page: Int
    ): Response<NetworkSearchResult>
}