package tw.roy.deng.codility.di.module

import dagger.Binds
import dagger.Module
import tw.roy.deng.codility.data.source.repository.UserRepository
import tw.roy.deng.codility.data.source.repository.UserRepositoryImpl

@Suppress("unused")
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun providesUserRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository
}