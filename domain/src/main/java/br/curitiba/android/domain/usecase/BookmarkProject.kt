package br.curitiba.android.domain.usecase

import br.curitiba.android.domain.repository.ProjectsRepository
import br.curitiba.android.domain.thread.ExecutionThread
import br.curitiba.android.domain.thread.PostExecutionThread
import br.curitiba.android.domain.usecase.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class BookmarkProject @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread,
    executionThread: ExecutionThread
) : CompletableUseCase<BookmarkProject.Params>(postExecutionThread, executionThread) {

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String) = Params(projectId)
        }
    }

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw BookmarkProjectParamsException()
        return projectsRepository.bookmarkProject(params.projectId)
    }

    class BookmarkProjectParamsException: IllegalArgumentException("Params can't be null!")
}
