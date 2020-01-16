package br.curitiba.android.data.mapper

import br.curitiba.android.data.model.ProjectData
import br.curitiba.android.domain.model.Project
import br.curitiba.android.data.fake.FakeProjectFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectMapperTest {

    private val mapper = ProjectMapper()

    @Test
    fun `it maps from Data Project to Domain Project`() {
        // arrange
        val projectData = FakeProjectFactory.makeProjectData()

        // act
        val project = mapper.mapToDomain(projectData)

        // assert
        assertProjectEquals(project, projectData)
    }

    @Test
    fun `it maps from Domain Project to Data Project`() {
        // arrange
        val project = FakeProjectFactory.makeProject()

        // act
        val projectData = mapper.mapToData(project)

        // assert
        assertProjectEquals(project, projectData)
    }

    private fun assertProjectEquals(domain: Project, data: ProjectData) {
        assertEquals(domain.id, data.id)
        assertEquals(domain.name, data.name)
        assertEquals(domain.fullName, data.fullName)
        assertEquals(domain.starCount, data.starCount)
        assertEquals(domain.dateCreated, data.dateCreated)
        assertEquals(domain.ownerName, data.ownerName)
        assertEquals(domain.ownerAvatar, data.ownerAvatar)
        assertEquals(domain.isBookmarked, data.isBookmarked)
    }
}