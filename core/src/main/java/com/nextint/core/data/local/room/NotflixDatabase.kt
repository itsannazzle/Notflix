package com.nextint.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nextint.core.data.local.entity.MoviesEntity
import com.nextint.core.data.local.entity.TvShowEntity

@Database(entities = [MoviesEntity::class, TvShowEntity::class], version = 2, exportSchema = false)
abstract class NotflixDatabase : RoomDatabase() {
    abstract fun notflixDao() : NotflixDao

}