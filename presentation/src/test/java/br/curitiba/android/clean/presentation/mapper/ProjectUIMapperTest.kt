package br.curitiba.android.clean.presentation.mapper

import br.curitiba.android.clean.presentation.test.FakeDataFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectUIMapperTest {

    private val projectMapper = ProjectUIMapper()

    @Test
    fun mapToViewMapsData() {
        val project = FakeDataFactory.makeProject()
        val projectForUi = projectMapper.mapToView(project)

        assertEquals(project.id, projectForUi.id)
        assertEquals(project.name, projectForUi.name)
        assertEquals(project.fullName, projectForUi.fullName)
        assertEquals(project.starCount, projectForUi.starCount)
        assertEquals(project.dateCreated, projectForUi.dateCreated)
        assertEquals(project.ownerName, projectForUi.ownerName)
        assertEquals(project.ownerAvatar, projectForUi.ownerAvatar)
        assertEquals(project.isBookmarked, projectForUi.isBookmarked)
    }
}