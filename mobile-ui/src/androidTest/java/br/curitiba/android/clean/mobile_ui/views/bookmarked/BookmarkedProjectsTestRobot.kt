package br.curitiba.android.clean.mobile_ui.views.bookmarked

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import br.curitiba.android.clean.mobile_ui.R
import br.curitiba.android.clean.mobile_ui.views.browse.BrowseAdapter

fun robot(func: BookmarkedProjectsTestRobot.() -> Unit) =
    BookmarkedProjectsTestRobot().apply { func() }

internal infix fun BookmarkedProjectsTestRobot.verify(func: BookmarkedProjectsTestResult.() -> Unit) =
    BookmarkedProjectsTestResult().apply { func() }


open class BookmarkedProjectsTestRobot {

    fun scrollToViewWithIndex(index: Int) {
        onView(ViewMatchers.withId(R.id.recycler_projects))
            .perform(RecyclerViewActions.scrollToPosition<BrowseAdapter.ViewHolder>(index))
    }
}