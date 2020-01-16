package br.curitiba.android.clean.domain.usecase

import br.curitiba.android.clean.domain.model.Project
import br.curitiba.android.clean.domain.repository.ProjectsRepository
import br.curitiba.android.clean.domain.thread.ExecutionThread
import br.curitiba.android.clean.domain.thread.PostExecutionThread
import br.curitiba.android.clean.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread,
    executionThread: ExecutionThread
) : ObservableUseCase<List<Project>, Nothing>(postExecutionThread, executionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}
