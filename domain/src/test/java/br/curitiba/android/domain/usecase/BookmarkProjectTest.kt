package br.curitiba.android.domain.usecase

import br.curitiba.android.domain.fake.FakeProjectFactory
import br.curitiba.android.domain.repository.ProjectsRepository
import br.curitiba.android.domain.thread.ExecutionThread
import br.curitiba.android.domain.thread.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class BookmarkProjectTest {

    @MockK lateinit var projectsRepository: ProjectsRepository
    @MockK lateinit var executionThread: ExecutionThread
    @MockK lateinit var postExecutionThread: PostExecutionThread

    private lateinit var bookmarkProject: BookmarkProject

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread, executionThread)
    }

    @Test
    fun `it completes`() {
        // arrange
        val projectToBeBookmarked = FakeProjectFactory.makeProject()
        val params = BookmarkProject.Params(projectToBeBookmarked.id)
        every { projectsRepository.bookmarkProject(projectToBeBookmarked.id) } returns Completable.complete()

        // act
        val testObserver = bookmarkProject.buildUseCaseCompletable(params).test()

        // assert
        testObserver.assertComplete()
    }

    @Test(expected = BookmarkProject.BookmarkProjectParamsException::class)
    fun `it throws BookmarkProjectParamsException when building with no params`() {
        bookmarkProject.buildUseCaseCompletable().test()
    }
}
