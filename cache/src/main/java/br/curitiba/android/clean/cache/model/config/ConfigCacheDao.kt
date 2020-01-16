package br.curitiba.android.clean.cache.model.config

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
abstract class ConfigCacheDao {

    @Query(ConfigCacheConstants.QUERY_CONFIG)
    abstract fun getConfig(): Single<ConfigCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: ConfigCache)
}