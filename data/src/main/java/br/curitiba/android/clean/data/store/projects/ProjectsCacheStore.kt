package br.curitiba.android.clean.data.store.projects

import br.curitiba.android.clean.data.cache.ProjectsCache
import br.curitiba.android.clean.data.model.ProjectData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheStore @Inject constructor(
    private val cache: ProjectsCache
) : ProjectsStore {

    override fun getProjects(): Observable<List<ProjectData>> {
        return cache.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectData>> {
        return cache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return cache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return cache.setProjectAsNotBookmarked(projectId)
    }

    override fun saveProjects(projects: List<ProjectData>): Completable {
        return cache.saveProjects(projects)
            .concatWith(cache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return cache.clearProjects()
    }
}
