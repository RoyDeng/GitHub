package tw.roy.deng.codility.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tw.roy.deng.codility.data.source.local.GitHubDatabase
import tw.roy.deng.codility.data.source.local.dao.UserDao
import javax.inject.Singleton

@Suppress("unused")
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun providesAppDatabase(
        application: Application
    ): GitHubDatabase = Room
        .databaseBuilder(
            application.applicationContext,
            GitHubDatabase::class.java,
            "GitHub.db"
        ).build()

    @Singleton
    @Provides
    fun providesUserDao(
        database: GitHubDatabase
    ): UserDao = database.userDao
}