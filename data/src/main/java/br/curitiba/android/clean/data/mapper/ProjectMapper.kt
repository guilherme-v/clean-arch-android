package br.curitiba.android.clean.data.mapper

import br.curitiba.android.clean.data.model.ProjectData
import br.curitiba.android.clean.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor(): DataMapper<ProjectData, Project> {

    override fun mapToDomain(data: ProjectData) = with(data) {
        Project(id, name, fullName, starCount, dateCreated, ownerName, ownerAvatar, isBookmarked)
    }

    override fun mapToData(domain: Project) = with(domain) {
        ProjectData(id, name, fullName, starCount, dateCreated, ownerName, ownerAvatar, isBookmarked)
    }
}
