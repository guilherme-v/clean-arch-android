package br.curitiba.android.data

import br.curitiba.android.data.cache.ProjectsCache
import br.curitiba.android.data.mapper.ProjectMapper
import br.curitiba.android.data.store.projects.ProjectsStoreFactory
import br.curitiba.android.domain.model.Project
import br.curitiba.android.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.rxkotlin.withLatestFrom
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val storeFactory: ProjectsStoreFactory
) : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return cache.areProjectsCached().toObservable().withLatestFrom(cache.isProjectsCacheExpired().toObservable())
            .flatMap { storeFactory.create(it.first, it.second).getProjects() }
            .flatMap { projects ->
                storeFactory.getProjectCacheStore()
                    .saveProjects(projects)
                    .andThen(Observable.just(projects))
            }
            .map { it.map(mapper::mapToDomain) }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return storeFactory.getProjectCacheStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return storeFactory.getProjectCacheStore().setProjectAsBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return storeFactory.getProjectCacheStore()
            .getBookmarkedProjects()
            .map { it.map(mapper::mapToDomain) }
    }
}