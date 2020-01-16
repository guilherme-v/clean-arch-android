package br.curitiba.android.data.store.projects

import br.curitiba.android.data.cache.ProjectsCache
import br.curitiba.android.data.fake.FakeProjectFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class ProjectsCacheStoreTest {

    @MockK lateinit var cache: ProjectsCache
    private lateinit var projectsCacheStore: ProjectsCacheStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        projectsCacheStore = ProjectsCacheStore(cache)
    }

    @Test
    fun `it should get cached Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        every { cache.getProjects() } returns Observable.just(fakeProjectDataList)

        // act
        val testObservable = projectsCacheStore.getProjects().test()

        // assert
        testObservable.assertValue(fakeProjectDataList)
    }

    @Test
    fun `it should get cached Bookmarked Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        every { cache.getBookmarkedProjects() } returns Observable.just(fakeProjectDataList)

        // act
        val testObservable = projectsCacheStore.getBookmarkedProjects().test()

        // assert
        testObservable.assertValue(fakeProjectDataList)
    }

    @Test
    fun `it should bookmark a cached Project`() {
        // arrange
        val fakeId = "FakeProjectId"
        every { cache.setProjectAsBookmarked(any()) } returns Completable.complete()

        // act
        val testObservable = projectsCacheStore.setProjectAsBookmarked(fakeId).test()

        // assert
        testObservable.assertComplete()
        verify { cache.setProjectAsBookmarked(fakeId) }
    }

    @Test
    fun `it should unbookmark a cached Project`() {
        // arrange
        val fakeId = "FakeProjectId"
        every { cache.setProjectAsNotBookmarked(any()) } returns Completable.complete()

        // act
        val testObservable = projectsCacheStore.setProjectAsNotBookmarked(fakeId).test()

        // assert
        testObservable.assertComplete()
        verify { cache.setProjectAsNotBookmarked(fakeId) }
    }

    @Test
    fun `it should save Projects in cache`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)

        // act
        val testObservable = projectsCacheStore.saveProjects(fakeProjectDataList).test()

        // assert
        testObservable.assertComplete()
        verify {
            cache.saveProjects(fakeProjectDataList)
            cache.setLastCacheTime(any())
        }
    }

    @Test
    fun `it should be able to clear the cache`() {
        // act
        val testObservable = projectsCacheStore.clearProjects().test()

        // assert
        testObservable.assertComplete()
        verify { cache.clearProjects() }
    }
}