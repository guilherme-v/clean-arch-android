package br.curitiba.android.domain.usecase.base

import br.curitiba.android.domain.thread.ExecutionThread
import br.curitiba.android.domain.thread.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<in Params>(
    private val postExecutionThread: PostExecutionThread,
    private val executionThread: ExecutionThread
): DisposableUseCase() {

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = buildUseCaseCompletable(params)
            .subscribeOn(executionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)

        addDisposable(completable.subscribeWith(observer))
    }
}
