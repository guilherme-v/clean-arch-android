package br.curitiba.android.clean.mobile_ui.views.bookmarked

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.curitiba.android.clean.mobile_ui.TestCleanArchApplication
import br.curitiba.android.clean.mobile_ui.fake.FakeFactory
import io.mockk.every
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkedProjectsFragmentTest {

    private val applicationComponent by lazy { TestCleanArchApplication.appComponent() }

    private val projectsRepository by lazy { applicationComponent.projectsRepository() }

    @Test
    fun itDisplayAListOfProjects() {

        // arrange
        val projects = FakeFactory.makeProjectList(10)
        every { projectsRepository.getBookmarkedProjects() } returns Observable.just(projects)

        // act
        launchFragmentInContainer<BookmarkedProjectsFragment>()

        // assert

        projects.forEachIndexed { index, project ->
            robot { scrollToViewWithIndex(index) } verify { itemShowText(project.fullName) }
        }
    }
}