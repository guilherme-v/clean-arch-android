package br.curitiba.android.clean.presentation.mapper

import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.presentation.model.ProjectUI
import javax.inject.Inject

open class ProjectUIMapper @Inject constructor() : UIMapper<ProjectUI, Project> {

    override fun mapToView(domain: Project) = with(domain) {
        ProjectUI(
            id,
            name,
            fullName,
            starCount,
            dateCreated,
            ownerName,
            ownerAvatar,
            isBookmarked
        )
    }
}