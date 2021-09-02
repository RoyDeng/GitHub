package tw.roy.deng.codility.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tw.roy.deng.codility.data.source.local.entity.DBUser

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<DBUser>?

    @Query("SELECT * FROM user WHERE login LIKE :q LIMIT 30 OFFSET :page * 30")
    suspend fun searchUsers(q: String, page: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg dbUser: DBUser)

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()
}