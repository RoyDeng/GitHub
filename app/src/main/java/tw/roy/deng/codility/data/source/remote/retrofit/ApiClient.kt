package tw.roy.deng.codility.data.source.remote.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.roy.deng.codility.data.source.remote.retrofit.ApiEndPoints.BASE_URL
import java.util.concurrent.TimeUnit

private val client = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

object GitHubApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(
            ApiService::class.java
        )
    }
}