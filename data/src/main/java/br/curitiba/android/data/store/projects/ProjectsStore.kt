package br.curitiba.android.data.store.projects

import br.curitiba.android.data.model.ProjectData
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsStore {

    fun getProjects(): Observable<List<ProjectData>>

    fun getBookmarkedProjects(): Observable<List<ProjectData>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun saveProjects(projects: List<ProjectData>): Completable

    fun clearProjects(): Completable
}