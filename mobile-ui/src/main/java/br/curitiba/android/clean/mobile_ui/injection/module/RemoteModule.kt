package br.curitiba.android.clean.mobile_ui.injection.module

import br.curitiba.android.clean.data.remote.ProjectsRemote
import br.curitiba.android.clean.mobile_ui.BuildConfig
import br.curitiba.android.clean.remote.ProjectsRemoteImpl
import br.curitiba.android.clean.remote.service.GithubTrendingService
import br.curitiba.android.clean.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }


    @Binds
    abstract fun bindProjectsRepository(repo: ProjectsRemoteImpl): ProjectsRemote
}

