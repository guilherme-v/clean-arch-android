package br.curitiba.android.data

import br.curitiba.android.data.mapper.ProjectMapper
import br.curitiba.android.data.store.projects.ProjectsStoreObservableFactory
import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val storeObservableFactory: ProjectsStoreObservableFactory
) : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return storeObservableFactory.create()
            .flatMap { store ->
                store.getProjects()
            }
            .flatMap { projects ->
                storeObservableFactory.cacheStore
                    .saveProjects(projects)
                    .andThen(Observable.just(projects))
            }
            .map { it.map(mapper::mapToDomain) }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return storeObservableFactory.cacheStore.setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return storeObservableFactory.cacheStore.setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return storeObservableFactory.cacheStore
            .getBookmarkedProjects()
            .map { it.map(mapper::mapToDomain) }
    }
}