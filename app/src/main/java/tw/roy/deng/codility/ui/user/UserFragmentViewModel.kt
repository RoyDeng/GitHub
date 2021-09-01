package tw.roy.deng.codility.ui.user

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.roy.deng.codility.data.model.User
import tw.roy.deng.codility.data.paging.UserDataSource
import tw.roy.deng.codility.data.source.repository.UserRepository
import javax.inject.Inject

class UserFragmentViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    val users: Flow<PagingData<User>> = getUsersStream()

    private fun getUsersStream(): Flow<PagingData<User>> {
        return Pager(
            PagingConfig(
                initialLoadSize = 30,
                pageSize = 30,
                enablePlaceholders = true
            )
        ) {
            UserDataSource(repository)
        }.flow
    }
}