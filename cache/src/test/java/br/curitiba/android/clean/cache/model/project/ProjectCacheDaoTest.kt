package br.curitiba.android.clean.cache.model.project

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.curitiba.android.clean.cache.db.AppDatabase
import br.curitiba.android.clean.cache.fake.FakeFactory
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P]) // https://stackoverflow.com/questions/56808485/robolectric-and-android-sdk-29/57261194#57261194
class ProjectCacheDaoTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: AppDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun `it should insert Projects into db`() {
        // arrange
        val projects = FakeFactory.makeProjectCacheList(10)
        database.projectsDao().insertProjects(projects)

        // act
        val testObserver = database.projectsDao().getProjects().test()

        // assert
        testObserver.assertValue(projects)
    }

    @Test
    fun `it should delete Projects from db`() {
        // arrange
        val projects = FakeFactory.makeProjectCacheList(10)
        database.projectsDao().insertProjects(projects)

        // act
        database.projectsDao().deleteProjects()
        val testObserver = database.projectsDao().getProjects().test()

        // assert
        testObserver.assertValue(emptyList())
    }

    @Test
    fun `it should get bookmarked Projects from db`() {
        // arrange
        val projects = FakeFactory.makeProjectCacheList(10).map { it.copy(isBookmarked = true) }
        database.projectsDao().insertProjects(projects)

        // act
        val testObserver = database.projectsDao().getBookmarkedProjects().test()

        // assert
        testObserver.assertValue(projects)
    }

    @Test
    fun `it should be able to bookmark Projects into db`() {
        // arrange
        val projects = FakeFactory.makeProjectCacheList(10).map { it.copy(isBookmarked = false) }
        val projectToBookmark = projects.first().copy(isBookmarked = true)
        database.projectsDao().insertProjects(projects)
        database.projectsDao().setBookmarkStatus(true, projectToBookmark.id)

        // act
        val testObserver = database.projectsDao().getBookmarkedProjects().test()

        // assert
        testObserver.assertValue(listOf(projectToBookmark))
    }

    @Test
    fun `it should be able to unbookmark Projects into db`() {
        // arrange
        val projects = FakeFactory.makeProjectCacheList(10).map { it.copy(isBookmarked = true) }
        val projectToUnbookmark = projects.first().copy(isBookmarked = false)
        database.projectsDao().insertProjects(projects)
        database.projectsDao().setBookmarkStatus(false, projectToUnbookmark.id)

        // act
        val testObserver = database.projectsDao().getBookmarkedProjects().test()

        // assert
        testObserver.assertValue(projects.drop(1))
    }
}