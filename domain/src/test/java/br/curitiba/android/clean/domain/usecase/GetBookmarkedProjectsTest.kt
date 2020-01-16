package br.curitiba.android.clean.domain.usecase

import br.curitiba.android.clean.domain.fake.FakeProjectFactory
import br.curitiba.android.clean.domain.repository.ProjectsRepository
import br.curitiba.android.clean.domain.thread.ExecutionThread
import br.curitiba.android.clean.domain.thread.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetBookmarkedProjectsTest {

    @MockK lateinit var projectsRepository: ProjectsRepository
    @MockK lateinit var executionThread: ExecutionThread
    @MockK lateinit var postExecutionThread: PostExecutionThread

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread, executionThread)
    }

    @Test
    fun `it completes`() {
        // arrange
        val projects = FakeProjectFactory.makeProjectList(2)
        every { projectsRepository.getBookmarkedProjects() } returns Observable.just(projects)

        // act
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()

        // assert
        testObserver.assertComplete()
    }

    @Test
    fun `it returns a list of bookmarked projects`() {
        // arrange
        val projects = FakeProjectFactory.makeProjectList(2)
        every { projectsRepository.getBookmarkedProjects() } returns Observable.just(projects)

        // act
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()

        // assert
        testObserver.assertValue(projects)
    }
}
