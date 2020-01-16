package br.curitiba.android.clean.data.store.projects

import br.curitiba.android.clean.data.cache.ProjectsCache
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsStoreObservableFactory @Inject constructor(
    private val cache: ProjectsCache,
    private val remoteStore: ProjectsRemoteStore,
    val cacheStore: ProjectsCacheStore
) {

    fun create(): Observable<ProjectsStore> {
        return Observable.zip(
            cache.isProjectsCacheExpired().toObservable(),
            cache.areProjectsCached().toObservable(),
            BiFunction { expired, cached ->
                if (cached && expired.not()) {
                    cacheStore
                } else {
                    remoteStore
                }
            }
        )
    }
}