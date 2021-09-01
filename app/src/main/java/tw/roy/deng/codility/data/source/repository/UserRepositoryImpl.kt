package tw.roy.deng.codility.data.source.repository

import android.app.Application
import tw.roy.deng.codility.utils.Result
import tw.roy.deng.codility.data.model.SearchResult
import tw.roy.deng.codility.data.model.User
import tw.roy.deng.codility.data.source.remote.UserRemoteDataSource
import tw.roy.deng.codility.mapper.SearchResultMapperRemote
import tw.roy.deng.codility.utils.SharedPreferenceHelper
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val application: Application,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    private val prefs = SharedPreferenceHelper.getInstance(application)

    override suspend fun searchUsers(page: Int): Result<SearchResult?> {
        val q = prefs.getSearchQuery().toString()
        val mapper = SearchResultMapperRemote()

        return when (val response = remoteDataSource.searchUsers(q, page)) {
            is Result.Success -> {
                if (response.data != null) {
                    val searchResult = mapper.transformToDomain(response.data)

                    Result.Success(searchResult)
                } else {
                    Result.Success(null)
                }
            }

            is Result.Fail -> {
                Result.Fail(response.message)
            }
        }
    }

    private suspend fun storeUsers(users: List<User>) {
//        val mapper = PostsMapperLocal()
//        mapper.transformToDto(posts).let { listOfDbPosts ->
//            listOfDbPosts.forEach {
//                localDataSource.insertPost(it)
//            }
//        }
    }
}