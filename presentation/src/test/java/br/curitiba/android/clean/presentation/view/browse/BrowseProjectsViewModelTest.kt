package br.curitiba.android.clean.presentation.view.browse

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.domain.usecase.BookmarkProject
import br.curitiba.android.clean.domain.usecase.GetProjects
import br.curitiba.android.clean.presentation.mapper.UIMapper
import br.curitiba.android.clean.presentation.model.ProjectUI
import br.curitiba.android.clean.presentation.resource.ResourceState
import br.curitiba.android.clean.presentation.test.FakeDataFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BrowseProjectsViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK lateinit var getProjects: GetProjects
    @MockK lateinit var bookmarkProject: BookmarkProject
    @MockK lateinit var projectUIMapper: UIMapper<ProjectUI, Project>

    lateinit var viewModel: BrowseProjectsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = BrowseProjectsViewModel(getProjects, bookmarkProject, projectUIMapper)
        viewModel.itemsResource // because we are using lazy we need to load this first
    }

    @Test
    fun `it should execute with success GetProjects usecase when loading Projects`() {
        // arrange
        val projectList = FakeDataFactory.makeProjectList(10)
        val projectUI = FakeDataFactory.makeProjectUI()
        val paramSlot = slot<DisposableObserver<List<Project>>>()
        every { getProjects.execute(capture(paramSlot)) } returns Unit
        every { projectUIMapper.mapToView(any()) } returns projectUI

        // act
        viewModel.loadProjects()
        paramSlot.captured.onNext(projectList)

        // assert
        val expectedList = projectList.map { projectUI }
        val itemsResource = viewModel.itemsResource
        verify { getProjects.execute(any()) }
        assertEquals(expectedList, itemsResource.value?.data)
        assertEquals(ResourceState.SUCCEEDED, itemsResource.value?.state)
    }

    @Test
    fun `it should execute with success BookmarkProject usecase when requested`() {
        // arrange
        val project = FakeDataFactory.makeProject()
        val paramSlot = slot<DisposableCompletableObserver>()
        val expectedParam = BookmarkProject.Params.forProject(project.id)
        every { bookmarkProject.execute(capture(paramSlot), any()) } returns Unit

        // act
        viewModel.bookmarkProject(project.id)
        paramSlot.captured.onComplete()

        // assert
        val itemsResource = viewModel.itemsResource
        verify { bookmarkProject.execute(capture(paramSlot), expectedParam) }
        assertEquals(ResourceState.SUCCEEDED, itemsResource.value?.state)
    }

    // TODO: Create tests to cover Errors cases...
}