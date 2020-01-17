package br.curitiba.android.clean.presentation.view.bookmarked

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.domain.usecase.GetBookmarkedProjects
import br.curitiba.android.clean.presentation.extensions.mutableLivedataOf
import br.curitiba.android.clean.presentation.mapper.ProjectUIMapper
import br.curitiba.android.clean.presentation.mapper.UIMapper
import br.curitiba.android.clean.presentation.model.ProjectUI
import br.curitiba.android.clean.presentation.resource.Resource
import br.curitiba.android.clean.presentation.resource.toFailed
import br.curitiba.android.clean.presentation.resource.toLoading
import br.curitiba.android.clean.presentation.resource.toSucceeded
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BookmarkedProjectsViewModel @Inject constructor(
    private val getBookmarkedProjects: GetBookmarkedProjects,
    private val projectUIMapper: ProjectUIMapper
) : ViewModel() {

    private val _itemsResource by lazy { mutableLivedataOf(Resource<List<ProjectUI>>()) }
    val itemsResource: LiveData<Resource<List<ProjectUI>>> by lazy { _itemsResource.also { loadBookmarkedProjects() } }

    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    fun loadBookmarkedProjects() {
        _itemsResource.toLoading()
        getBookmarkedProjects.execute(LoadProjectsObserver())
    }

    inner class LoadProjectsObserver : DisposableObserver<List<Project>>() {
        override fun onComplete() {}

        override fun onNext(it: List<Project>) {
            val listOfProjectUI = it.map(projectUIMapper::mapToView)
            _itemsResource.toSucceeded(listOfProjectUI)
        }

        override fun onError(e: Throwable) {
            _itemsResource.toFailed(e.localizedMessage)
        }
    }
}