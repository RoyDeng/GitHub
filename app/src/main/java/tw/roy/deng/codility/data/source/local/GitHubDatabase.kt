package tw.roy.deng.codility.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tw.roy.deng.codility.data.source.local.dao.UserDao
import tw.roy.deng.codility.data.source.local.entity.DBUser
import tw.roy.deng.codility.utils.typeconverters.TypesConverter
import tw.roy.deng.codility.utils.typeconverters.ValuesConverter

@Database(entities = [DBUser::class], version = 1, exportSchema = false)
@TypeConverters(
    TypesConverter::class,
    ValuesConverter::class
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}