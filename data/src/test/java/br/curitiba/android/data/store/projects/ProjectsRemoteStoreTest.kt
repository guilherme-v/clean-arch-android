package br.curitiba.android.data.store.projects

import br.curitiba.android.data.remote.ProjectsRemote
import br.curitiba.android.data.fake.FakeProjectFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class ProjectsRemoteStoreTest {

    @MockK lateinit var remote: ProjectsRemote
    lateinit var projectsRemoteStore: ProjectsRemoteStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        projectsRemoteStore = ProjectsRemoteStore(remote)
    }

    @Test
    fun `it should return a list of Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        every { remote.getProjects() } returns Observable.just(fakeProjectDataList)

        // act
        val testObservable = projectsRemoteStore.getProjects().test()

        // assert
        testObservable.assertComplete()
        testObservable.assertValue(fakeProjectDataList)
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to get a bookmarked Project`() {
        projectsRemoteStore.getBookmarkedProjects()
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to bookmarked a Project`() {
        projectsRemoteStore.setProjectAsBookmarked("dummyId")
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to unbookmark a Project`() {
        projectsRemoteStore.setProjectAsNotBookmarked("dummyId")
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to save a list of Projects`() {
        projectsRemoteStore.saveProjects(listOf())
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to clear`() {
        projectsRemoteStore.clearProjects()
    }
}