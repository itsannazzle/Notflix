package com.nextint.core.di

import androidx.room.Room
import com.nextint.core.data.MoviesRepositories
import com.nextint.core.data.local.LocalDataSource
import com.nextint.core.data.local.room.NotflixDatabase
import com.nextint.core.data.remote.RemoteDataSource
import com.nextint.core.data.remote.config.ApiRequest
import com.nextint.core.domain.repository.NotflixImpl
import com.nextint.core.utils.AppExecutor
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphare : ByteArray = net.sqlcipher.database.SQLiteDatabase.getBytes("Notflix".toCharArray())
        val factory = SupportFactory(passphare)
        Room.databaseBuilder(
            androidContext(),
            NotflixDatabase::class.java,
            "NotflixDatabase"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname,"sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    single<NotflixImpl> {
        MoviesRepositories(
            get(),
            get(),
            get()
        )
    }
}