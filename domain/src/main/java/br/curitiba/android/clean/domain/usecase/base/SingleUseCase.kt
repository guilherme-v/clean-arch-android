package br.curitiba.android.clean.domain.usecase.base

import br.curitiba.android.clean.domain.thread.PostExecutionThread
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread,
    private val executionThread: PostExecutionThread
) : DisposableUseCase() {

    abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        val single = this.buildUseCaseSingle(params)
            .subscribeOn(executionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)

        addDisposable(single.subscribeWith(observer))
    }
}
