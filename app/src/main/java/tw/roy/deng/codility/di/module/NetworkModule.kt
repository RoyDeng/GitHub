package tw.roy.deng.codility.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.roy.deng.codility.data.source.remote.retrofit.ApiService
import tw.roy.deng.codility.data.source.remote.retrofit.ApiEndPoints.BASE_URL
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideKEYPOApi(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)
}