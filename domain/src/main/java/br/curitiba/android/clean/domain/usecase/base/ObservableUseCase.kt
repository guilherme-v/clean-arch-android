package br.curitiba.android.clean.domain.usecase.base

import br.curitiba.android.clean.domain.thread.ExecutionThread
import br.curitiba.android.clean.domain.thread.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, in Params>(
    private val postExecutionThread: PostExecutionThread,
    private val executionThread: ExecutionThread
) : DisposableUseCase() {

    abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        dispose()

        val observable = buildUseCaseObservable(params)
            .subscribeOn(executionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }
}
