package br.curitiba.android.clean.data.store.projects

import br.curitiba.android.clean.data.model.ProjectData
import br.curitiba.android.clean.data.remote.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteStore @Inject constructor(
    private val remote: ProjectsRemote
): ProjectsStore {

    override fun getProjects(): Observable<List<ProjectData>> {
        return remote.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectData>> {
        throw ProjectsRemoteStoreException("Getting bookmarked projects isn't supported")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw ProjectsRemoteStoreException("Setting as bookmarked isn't supported")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw ProjectsRemoteStoreException("Setting as not bookmarked isn't supported")
    }

    override fun saveProjects(projects: List<ProjectData>): Completable {
        throw ProjectsRemoteStoreException("Saving isn't supported")
    }

    override fun clearProjects(): Completable {
        throw ProjectsRemoteStoreException("Clearing isn't supported")
    }


    class ProjectsRemoteStoreException(message: String) : UnsupportedOperationException(message)
}