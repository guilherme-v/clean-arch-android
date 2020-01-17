package br.curitiba.android.clean.mobile_ui.thread

import br.curitiba.android.clean.domain.thread.ExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IOThread @Inject constructor() : ExecutionThread {

    override val scheduler: Scheduler = Schedulers.io()
}