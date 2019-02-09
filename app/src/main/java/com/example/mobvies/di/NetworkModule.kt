package com.example.mobvies.di

import android.content.Context
import com.example.mobvies.remote.Api
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


private const val CLIENT_TIME_OUT = 120L
private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
private const val CLIENT_CACHE_DIRECTORY = "http"


val networkModule = module {
    single { createCache(androidContext()) }
    single { createChuckInterceptor(androidContext()) }
    single("retrofitClient") { createOkHttpClient(get(), get()) }
    single { createWebService<Api>(get(name = "retrofitClient"), get(name = "baseUrl")) }

}


/**
 * Create Cache object for OkHttp instance
 */
fun createCache(context: Context): Cache = Cache(
    File(
        context.cacheDir,
        CLIENT_CACHE_DIRECTORY
    ),
    CLIENT_CACHE_SIZE
)


fun createChuckInterceptor(context: Context): ChuckInterceptor {
    return ChuckInterceptor(context)
}

/**
 * Create OkHttp client with required interceptors and defined timeouts
 */
fun createOkHttpClient(
    cache: Cache,
    chuckInterceptor: ChuckInterceptor
): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
    okHttpBuilder
        .addInterceptor(chuckInterceptor)
        .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .cache(cache)
    return okHttpBuilder.build()
}


/**
 * Create Retrofit Client
 *
 * <reified T> private func let us using reflection.
 * We can use generics and reflection so ->
 *  we don't have to define required Api Interface here
 */
inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
