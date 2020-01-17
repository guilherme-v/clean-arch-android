package br.curitiba.android.clean.mobile_ui.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.domain.thread.ExecutionThread
import br.curitiba.android.clean.domain.thread.PostExecutionThread
import br.curitiba.android.clean.mobile_ui.injection.annotations.ViewModelKey
import br.curitiba.android.clean.mobile_ui.injection.factory.ViewModelFactory
import br.curitiba.android.clean.mobile_ui.thread.AndroidMainThread
import br.curitiba.android.clean.mobile_ui.thread.IOThread
import br.curitiba.android.clean.presentation.mapper.ProjectUIMapper
import br.curitiba.android.clean.presentation.mapper.UIMapper
import br.curitiba.android.clean.presentation.model.ProjectUI
import br.curitiba.android.clean.presentation.view.bookmarked.BookmarkedProjectsViewModel
import br.curitiba.android.clean.presentation.view.browse.BrowseProjectsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    // ViewModels
    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowseProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkedProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(viewModel: BookmarkedProjectsViewModel): ViewModel

    // Factories
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // Mappers
    @Binds
    abstract fun bindProjectUIMapper(mapper: ProjectUIMapper): UIMapper<ProjectUI, Project>
}