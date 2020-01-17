package br.curitiba.android.clean.presentation.view.bookmarked

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.domain.usecase.GetBookmarkedProjects
import br.curitiba.android.clean.presentation.mapper.ProjectUIMapper
import br.curitiba.android.clean.presentation.resource.ResourceState
import br.curitiba.android.clean.presentation.test.FakeDataFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookmarkedProjectsViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @MockK lateinit var projectUIMapper: ProjectUIMapper

    lateinit var viewModel: BookmarkedProjectsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = BookmarkedProjectsViewModel(getBookmarkedProjects, projectUIMapper)
        viewModel.itemsResource // because we are using lazy we need to load this first
    }

    @Test
    fun `it should execute with success GetBookmarkedProjects usecase when loading Projects`() {
        // arrange
        val projectList = FakeDataFactory.makeProjectList(10)
        val projectUI = FakeDataFactory.makeProjectUI()
        val paramSlot = slot<DisposableObserver<List<Project>>>()
        every { getBookmarkedProjects.execute(capture(paramSlot)) } returns Unit
        every { projectUIMapper.mapToView(any()) } returns projectUI

        // act
        viewModel.loadBookmarkedProjects()
        paramSlot.captured.onNext(projectList)

        // assert
        val expectedList = projectList.map { projectUI }
        val itemsResource = viewModel.itemsResource
        verify { getBookmarkedProjects.execute(any()) }
        assertEquals(expectedList, itemsResource.value?.data)
        assertEquals(ResourceState.SUCCEEDED, itemsResource.value?.state)
    }

    // TODO: Create tests to cover Errors cases...
}