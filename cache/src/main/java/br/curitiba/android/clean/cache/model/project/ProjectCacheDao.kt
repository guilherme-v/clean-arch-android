package br.curitiba.android.clean.cache.model.project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.curitiba.android.clean.cache.model.project.ProjectCacheConstants.DELETE_PROJECTS
import br.curitiba.android.clean.cache.model.project.ProjectCacheConstants.QUERY_BOOKMARKED_PROJECTS
import br.curitiba.android.clean.cache.model.project.ProjectCacheConstants.QUERY_PROJECTS
import br.curitiba.android.clean.cache.model.project.ProjectCacheConstants.QUERY_UPDATE_BOOKMARK_STATUS
import io.reactivex.Observable

@Dao
abstract class ProjectCacheDao {

    @JvmSuppressWildcards
    @Query(QUERY_PROJECTS)
    abstract fun getProjects(): Observable<List<ProjectCache>>

    @JvmSuppressWildcards
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects(projects: List<ProjectCache>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Observable<List<ProjectCache>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean, projectId: String)
}