package br.curitiba.android.clean.mobile_ui.injection.module

import br.curitiba.android.clean.data.ProjectsDataRepository
import br.curitiba.android.clean.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindProjectsRepository(repo: ProjectsDataRepository): ProjectsRepository
}