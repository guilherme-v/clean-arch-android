package br.curitiba.android.remote

import br.curitiba.android.clean.data.model.ProjectData
import br.curitiba.android.clean.data.remote.ProjectsRemote
import br.curitiba.android.remote.mapper.ProjectsResponseDTOMapper
import br.curitiba.android.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val mapper: ProjectsResponseDTOMapper,
    private val service: GithubTrendingService
) : ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectData>> {
        return service.searchRepositories(
            query = "language:kotlin",
            order = "stars",
            sortBy = "desc"
        ).map { it.items.map(mapper::mapToData) }
    }
}