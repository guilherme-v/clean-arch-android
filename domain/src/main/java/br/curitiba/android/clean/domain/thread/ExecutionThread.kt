package br.curitiba.android.clean.domain.thread

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}