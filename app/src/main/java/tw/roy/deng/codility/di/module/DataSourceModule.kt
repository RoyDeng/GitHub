package tw.roy.deng.codility.di.module

import dagger.Binds
import dagger.Module
import tw.roy.deng.codility.data.source.remote.UserRemoteDataSource
import tw.roy.deng.codility.data.source.remote.UserRemoteDataSourceImpl

@Suppress("unused")
@Module
abstract class DataSourceModule {
    @Binds
    abstract fun providesUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource
}