package br.curitiba.android.clean.mobile_ui.views.browse

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import br.curitiba.android.clean.mobile_ui.R

class BrowseProjectsTestResult {

    fun itemShowText(text: String) {
        onView(ViewMatchers.withId(R.id.projects_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(text))))
    }
}