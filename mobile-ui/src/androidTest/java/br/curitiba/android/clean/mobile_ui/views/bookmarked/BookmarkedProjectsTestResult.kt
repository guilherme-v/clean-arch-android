package br.curitiba.android.clean.mobile_ui.views.bookmarked

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import br.curitiba.android.clean.mobile_ui.R

class BookmarkedProjectsTestResult {

    fun itemShowText(text: String) {
        onView(ViewMatchers.withId(R.id.recycler_projects))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(text))))
    }
}