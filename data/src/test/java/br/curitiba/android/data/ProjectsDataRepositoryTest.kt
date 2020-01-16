package br.curitiba.android.data

import br.curitiba.android.data.mapper.ProjectMapper
import br.curitiba.android.data.store.projects.ProjectsCacheStore
import br.curitiba.android.data.store.projects.ProjectsStore
import br.curitiba.android.data.store.projects.ProjectsStoreObservableFactory
import br.curitiba.android.data.fake.FakeProjectFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class ProjectsDataRepositoryTest {

    @MockK private lateinit var mapper: ProjectMapper
    @MockK lateinit var store: ProjectsStore
    @MockK lateinit var cacheStore: ProjectsCacheStore
    @MockK lateinit var storeObservableFactory: ProjectsStoreObservableFactory

    private lateinit var repository: ProjectsDataRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repository = ProjectsDataRepository(mapper, storeObservableFactory)
    }

    @Test
    fun `it should return a list of Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        val fakeProject = FakeProjectFactory.makeProject()
        every { storeObservableFactory.create() } returns Observable.just(store)
        every { store.getProjects() } returns Observable.just(fakeProjectDataList)
        every { storeObservableFactory.cacheStore } returns cacheStore
        every { cacheStore.saveProjects(any()) } returns Completable.complete()
        every { mapper.mapToDomain(any()) } returns fakeProject

        // act
        val testObservable = repository.getProjects().test()

        // assert
        val expectedList = fakeProjectDataList.map { fakeProject }
        testObservable.assertValue(expectedList)
    }

    @Test
    fun `it should bookmark a project`() {
        // arrange
        val fakeId = "fakeProjectId"
        every { storeObservableFactory.cacheStore } returns cacheStore

        // act
        repository.bookmarkProject(fakeId).test()

        // assert
        verify { cacheStore.setProjectAsBookmarked(fakeId) }
    }

    @Test
    fun `it should unbookmark a project`() {
        // arrange
        val fakeId = "fakeProjectId"
        every { storeObservableFactory.cacheStore } returns cacheStore

        // act
        repository.unbookmarkProject(fakeId).test()

        // assert
        verify { cacheStore.setProjectAsNotBookmarked(fakeId) }
    }

    @Test
    fun `it should return a list of bookmarked Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        val fakeProject = FakeProjectFactory.makeProject()
        every { storeObservableFactory.cacheStore } returns cacheStore
        every { cacheStore.getBookmarkedProjects() } returns Observable.just(fakeProjectDataList)
        every { mapper.mapToDomain(any()) } returns fakeProject

        // act
        val testObservable = repository.getBookmarkedProjects().test()

        // assert
        val expectedList = fakeProjectDataList.map { fakeProject }
        testObservable.assertValue(expectedList)
    }
}