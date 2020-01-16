package br.curitiba.android.clean.cache.mapper

import br.curitiba.android.clean.cache.model.project.ProjectCache
import br.curitiba.android.clean.data.model.ProjectData
import javax.inject.Inject

class ProjectCacheMapper @Inject constructor() : CacheMapper<ProjectCache, ProjectData> {

    override fun mapToData(cache: ProjectCache) = with(cache) {
        ProjectData(
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

    override fun mapToCache(data: ProjectData) = with(data) {
        ProjectCache(
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