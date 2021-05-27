package com.nextint.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nextint.core.data.local.entity.MoviesEntity
import com.nextint.core.data.local.entity.TvShowEntity

@Database(entities = [MoviesEntity::class, TvShowEntity::class], version = 2, exportSchema = false)
abstract class NotflixDatabase : RoomDatabase() {
    abstract fun notflixDao() : NotflixDao
    companion object{
        @Volatile
        private var instance: NotflixDatabase? = null

        fun getDBInstance(context: Context) : NotflixDatabase{
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotflixDatabase::class.java,
                        "NotflixDatabase"
                    ) .build()
                }
            }
            return instance as NotflixDatabase
        }
    }
}