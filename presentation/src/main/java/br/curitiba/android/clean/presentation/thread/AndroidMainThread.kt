package br.curitiba.android.clean.presentation.thread

import br.curitiba.android.clean.domain.thread.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidMainThread : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}