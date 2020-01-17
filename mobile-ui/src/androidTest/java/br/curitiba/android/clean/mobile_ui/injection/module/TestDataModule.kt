package br.curitiba.android.clean.mobile_ui.injection.module

import br.curitiba.android.clean.domain.repository.ProjectsRepository
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
abstract class TestDataModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun bindProjectsRepository(): ProjectsRepository = mockk()
    }
}