package br.curitiba.android.data.mapper

import br.curitiba.android.data.model.ProjectData
import br.curitiba.android.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor(): DataMapper<ProjectData, Project> {

    override fun mapToDomain(data: ProjectData) = with(data) {
        Project(id, name, fullName, starCount, dateCreated, ownerName, ownerAvatar, isBookmarked)
    }

    override fun mapToData(domain: Project) = with(domain) {
        ProjectData(id, name, fullName, starCount, dateCreated, ownerName, ownerAvatar, isBookmarked)
    }
}
