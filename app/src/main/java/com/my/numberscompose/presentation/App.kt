package com.my.numberscompose.presentation

import android.app.Application
import androidx.room.Room
import com.my.numberscompose.data.storage.DB.HistoryDataBase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {
    lateinit var retrofit: Retrofit
    lateinit var db: HistoryDataBase
    override fun onCreate() {
        super.onCreate()
        application = this
        val interceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("http://numbersapi.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        db = Room.databaseBuilder(
            context = this,
            klass = HistoryDataBase::class.java,
            name = "history_database"
        ).build()


    }

    companion object {
        private lateinit var application: App
        fun getInstance() = application
    }
}