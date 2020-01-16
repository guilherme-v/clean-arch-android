package br.curitiba.android.clean.cache.model.config

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ConfigCacheConstants.TABLE_NAME)
data class ConfigCache(

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,

    var lastCacheTime: Long
)