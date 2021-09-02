package tw.roy.deng.codility.data.source.local

import tw.roy.deng.codility.data.source.local.entity.DBUser

interface UserLocalDataSource {
    suspend fun getUsers(): List<DBUser>?
    suspend fun insertUser(user: DBUser)
    suspend fun deleteUsers()
}