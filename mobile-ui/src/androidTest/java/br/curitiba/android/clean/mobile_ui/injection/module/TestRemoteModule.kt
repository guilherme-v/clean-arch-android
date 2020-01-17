package br.curitiba.android.clean.mobile_ui.injection.module

import br.curitiba.android.clean.data.remote.ProjectsRemote
import br.curitiba.android.clean.remote.service.GithubTrendingService
import dagger.Module
import dagger.Provides
import io.mockk.mockk

@Module
abstract class TestRemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService = mockk()

        @Provides
        fun bindProjectsRepository(): ProjectsRemote = mockk()
    }
}
