package br.curitiba.android.clean.cache.model.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProjectCacheConstants.TABLE_NAME)
data class ProjectCache(

    @PrimaryKey
    @ColumnInfo(name = ProjectCacheConstants.COLUMN_PROJECT_ID)
    var id: String,

    var name: String,

    var fullName: String,

    var starCount: String,

    var dateCreated: String,

    var ownerName: String,

    var ownerAvatar: String,

    @ColumnInfo(name = ProjectCacheConstants.COLUMN_IS_BOOKMARKED)
    var isBookmarked: Boolean
)