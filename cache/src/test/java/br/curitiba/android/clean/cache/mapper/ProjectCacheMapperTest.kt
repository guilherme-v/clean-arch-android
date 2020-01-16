package br.curitiba.android.clean.cache.mapper

import br.curitiba.android.clean.cache.fake.FakeFactory
import br.curitiba.android.clean.cache.model.project.ProjectCache
import br.curitiba.android.clean.data.model.ProjectData
import org.junit.Assert.*
import org.junit.Test

class ProjectCacheMapperTest {
    private val mapper = ProjectCacheMapper()

    @Test
    fun `it maps from project cache to project data`() {
        val cache = FakeFactory.makeProjectCache()
        val data = mapper.mapToData(cache)

        assertEqualData(cache, data)
    }

    @Test
    fun `it maps from project data to project cache`() {
        val cache = FakeFactory.makeProjectData()
        val model = mapper.mapToCache(cache)

        assertEqualData(model, cache)
    }

    private fun assertEqualData(model: ProjectCache, cache: ProjectData) {
        assertEquals(model.id, cache.id)
        assertEquals(model.fullName, cache.fullName)
        assertEquals(model.name, cache.name)
        assertEquals(model.dateCreated, cache.dateCreated)
        assertEquals(model.starCount, cache.starCount)
        assertEquals(model.isBookmarked, cache.isBookmarked)
        assertEquals(model.ownerName, cache.ownerName)
        assertEquals(model.ownerAvatar, cache.ownerAvatar)
    }
}