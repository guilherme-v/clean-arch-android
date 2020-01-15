package br.curitiba.android.domain.usecase

import br.curitiba.android.domain.model.Project
import br.curitiba.android.domain.repository.ProjectsRepository
import br.curitiba.android.domain.thread.ExecutionThread
import br.curitiba.android.domain.thread.PostExecutionThread
import br.curitiba.android.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread,
    executionThread: ExecutionThread
) : ObservableUseCase<List<Project>, Unit>(postExecutionThread, executionThread) {

    public override fun buildUseCaseObservable(params: Unit?): Observable<List<Project>>
            = projectsRepository.getBookmarkedProjects()
}