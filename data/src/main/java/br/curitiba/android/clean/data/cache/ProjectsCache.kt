package br.curitiba.android.clean.data.cache

import br.curitiba.android.clean.data.model.ProjectData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsCache {

    fun getProjects(): Observable<List<ProjectData>> 

    fun saveProjects(projects: List<ProjectData>): Completable 

    fun clearProjects(): Completable  

    fun getBookmarkedProjects(): Observable<List<ProjectData>> 

    fun setProjectAsBookmarked(projectId: String): Completable 

    fun setProjectAsNotBookmarked(projectId: String): Completable 

    fun areProjectsCached(): Single<Boolean> 

    fun setLastCacheTime(lastCache: Long): Completable 

    fun isProjectsCacheExpired(): Single<Boolean> 
}
