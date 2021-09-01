package tw.roy.deng.codility.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tw.roy.deng.codility.data.model.User
import tw.roy.deng.codility.data.source.repository.UserRepository
import tw.roy.deng.codility.utils.Result
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val repository: UserRepository
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val nextPage = params.key ?: 1

        return when (val result = repository.searchUsers(nextPage)) {
            is Result.Success -> {
                if (result.data != null) {
                    val users = result.data.items

                    return if (users.isNotEmpty()) {
                        LoadResult.Page(
                            data = users,
                            prevKey = if (nextPage == 1) null else nextPage - 1 ,
                            nextKey = nextPage.plus(1)
                        )
                    } else {
                        LoadResult.Page(
                            data = emptyList(),
                            prevKey = if (nextPage == 1) null else nextPage - 1 ,
                            nextKey = null
                        )
                    }
                } else {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = if (nextPage == 1) null else nextPage - 1 ,
                        nextKey = null
                    )
                }
            }

            is Result.Fail -> {
                return LoadResult.Error(Exception(result.message))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}