package br.curitiba.android.clean.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.curitiba.android.clean.cache.model.config.ConfigCache
import br.curitiba.android.clean.cache.model.config.ConfigCacheDao
import br.curitiba.android.clean.cache.model.project.ProjectCache
import br.curitiba.android.clean.cache.model.project.ProjectCacheDao

@Database(entities = [ProjectCache::class, ConfigCache::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun projectsDao(): ProjectCacheDao

    abstract fun configDao(): ConfigCacheDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "app-clean-arch.db"
                        ).build()
                    }
                    return INSTANCE as AppDatabase
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}