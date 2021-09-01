package tw.roy.deng.codility.data.source.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import tw.roy.deng.codility.data.model.NetworkSearchResult
import tw.roy.deng.codility.data.source.remote.retrofit.ApiService
import tw.roy.deng.codility.utils.Result
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val retrofitClient: ApiService
) : UserRemoteDataSource {
    override suspend fun searchUsers(q: String, page: Int): Result<NetworkSearchResult?> = withContext(ioDispatcher) {
        return@withContext try {
            val result = retrofitClient.searchUsers(q, page)

            if (result.isSuccessful) {
                val networkSearchResult = result.body()

                Result.Success(networkSearchResult)
            } else {
                val errorBody = JSONObject(result.errorBody()!!.string())
                errorBody.getString("message")?.let {
                    Result.Fail(it)
                } ?: Result.Fail("目前無法搜尋用戶，請稍後再試。")
            }
        } catch (exception: Exception) {
            Result.Fail("搜尋用戶發生錯誤: $exception")
        }
    }
}