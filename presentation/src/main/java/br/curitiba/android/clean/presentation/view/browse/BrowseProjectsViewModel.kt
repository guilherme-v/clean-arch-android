package br.curitiba.android.clean.presentation.view.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.domain.usecase.BookmarkProject
import br.curitiba.android.clean.domain.usecase.GetProjects
import br.curitiba.android.clean.presentation.extensions.mutableLivedataOf
import br.curitiba.android.clean.presentation.mapper.UIMapper
import br.curitiba.android.clean.presentation.model.ProjectUI
import br.curitiba.android.clean.presentation.resource.Resource
import br.curitiba.android.clean.presentation.resource.toFailed
import br.curitiba.android.clean.presentation.resource.toLoading
import br.curitiba.android.clean.presentation.resource.toSucceeded
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
    private val getProjects: GetProjects,
    private val bookmarkProject: BookmarkProject,
    private val projectUIMapper: UIMapper<ProjectUI, Project>
) : ViewModel() {

    private val _itemsResource by lazy { mutableLivedataOf(Resource<List<ProjectUI>>()) }
    val itemsResource: LiveData<Resource<List<ProjectUI>>> by lazy { _itemsResource.also { loadProjects() } }

    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }

    fun loadProjects() {
        _itemsResource.toLoading()
        getProjects.execute(LoadProjectsObserver())
    }

    fun bookmarkProject(projectId: String) {
        _itemsResource.toLoading()
        val params = BookmarkProject.Params.forProject(projectId)
        bookmarkProject.execute(BookmarkProjectObserver(), params)
    }

    // Can this be embedded into execute?
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

    inner class BookmarkProjectObserver : DisposableCompletableObserver() {
        override fun onComplete() {
            _itemsResource.toSucceeded(_itemsResource.value?.data)
        }

        override fun onError(e: Throwable) {
            _itemsResource.toFailed(e.localizedMessage)
        }
    }
}