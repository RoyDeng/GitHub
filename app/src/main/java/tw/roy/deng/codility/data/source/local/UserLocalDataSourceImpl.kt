package tw.roy.deng.codility.data.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import tw.roy.deng.codility.data.source.local.dao.UserDao
import tw.roy.deng.codility.data.source.local.entity.DBUser
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher
) : UserLocalDataSource {
    override suspend fun getUsers(): List<DBUser>? = withContext(ioDispatcher) {
        return@withContext userDao.getUsers()
    }

    override suspend fun insertUser(user: DBUser) = withContext(ioDispatcher) {
        userDao.insertUser(user)
    }

    override suspend fun deleteUsers() = withContext(ioDispatcher) {
        userDao.deleteAllUsers()
    }
}