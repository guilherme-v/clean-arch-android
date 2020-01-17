package br.curitiba.android.clean.mobile_ui.injection.module

import br.curitiba.android.clean.domain.thread.ExecutionThread
import br.curitiba.android.clean.domain.thread.PostExecutionThread
import br.curitiba.android.clean.mobile_ui.thread.AndroidMainThread
import br.curitiba.android.clean.mobile_ui.thread.IOThread
import br.curitiba.android.clean.mobile_ui.views.MainActivity
import br.curitiba.android.clean.mobile_ui.views.bookmarked.BookmarkedProjectsFragment
import br.curitiba.android.clean.mobile_ui.views.browse.BrowseProjectsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MobileUIModule {

    // Threads
    @Binds
    abstract fun bindPostExecutionThread(androidMainThread: AndroidMainThread): PostExecutionThread

    @Binds
    abstract fun IOThread(uiThread: IOThread): ExecutionThread

    // Activities + Fragments
    @ContributesAndroidInjector
    abstract fun contributesBrowseProjectsFragment(): BrowseProjectsFragment

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedProjectsFragment(): BookmarkedProjectsFragment

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}
