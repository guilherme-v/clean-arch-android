package br.curitiba.android.remote.mapper

import br.curitiba.android.clean.data.model.ProjectData
import br.curitiba.android.remote.model.ProjectDTO
import javax.inject.Inject

class ProjectsResponseDTOMapper @Inject constructor() : DTOMapper<ProjectDTO, ProjectData> {

    override fun mapToData(dto: ProjectDTO) = with(dto) {
        ProjectData(
            id,
            name,
            fullName,
            starCount.toString(),
            dateCreated,
            owner.ownerName,
            owner.ownerAvatar,
            false
        )
    }
}
