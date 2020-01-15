package br.curitiba.android.domain.thread

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}