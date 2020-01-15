package br.curitiba.android.data.store.projects

import javax.inject.Inject

class ProjectsStoreFactory @Inject constructor(
    private val cacheStore: ProjectsCacheStore,
    private val remoteStore: ProjectsRemoteStore
){
    fun create(projectsAreCached: Boolean, cacheExpired: Boolean) : ProjectsStore {
        return if (projectsAreCached && cacheExpired.not()) {
            cacheStore
        } else {
            remoteStore
        }
    }

    fun getProjectCacheStore() = cacheStore
}