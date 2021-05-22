package com.example.notflix.core.di

import androidx.room.Room
import com.example.notflix.core.data.MoviesRepositories
import com.example.notflix.core.data.local.LocalDataSource
import com.example.notflix.core.data.local.room.NotflixDatabase
import com.example.notflix.core.data.remote.RemoteDataSource
import com.example.notflix.core.data.remote.config.ApiRequest
import com.example.notflix.core.domain.repository.NotflixImpl
import com.example.notflix.utils.AppExecutor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NotflixDatabase>().notflixDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NotflixDatabase::class.java,
            "NotflixDatabase"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiRequest::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutor() }
    single<NotflixImpl> { MoviesRepositories(get(),get(),get()) }
}