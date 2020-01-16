package br.curitiba.android.clean.data.store.projects

import br.curitiba.android.clean.data.cache.ProjectsCache
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class ProjectsStoreObservableFactoryTest {

    @MockK lateinit var cache: ProjectsCache
    @MockK lateinit var remoteStore: ProjectsRemoteStore
    @MockK lateinit var cacheStore: ProjectsCacheStore

    lateinit var factory: ProjectsStoreObservableFactory

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        factory = ProjectsStoreObservableFactory(cache, remoteStore, cacheStore)
    }

    @Test
    fun `it should return cacheStore when projects are cached and not expired`() {
        // arrange
        every { cache.isProjectsCacheExpired() } returns Single.just(false)
        every { cache.areProjectsCached() } returns Single.just(true)

        // act
        val testObservable = factory.create().test()

        // assert
        testObservable.assertValue(cacheStore)
    }

    @Test
    fun `it should return remoteStore when there are any project cached`() {
        // arrange
        every { cache.areProjectsCached() } returns Single.just(false)
        every { cache.isProjectsCacheExpired() } returns Single.just(true)

        // act
        val testObservable = factory.create().test()

        // assert
        testObservable.assertValue(remoteStore)
    }

    @Test
    fun `it should return remoteStore if there are projects cached but they are expired`() {
        // arrange
        every { cache.areProjectsCached() } returns Single.just(true)
        every { cache.isProjectsCacheExpired() } returns Single.just(true)

        // act
        val testObservable = factory.create().test()

        // assert
        testObservable.assertValue(remoteStore)
    }
}