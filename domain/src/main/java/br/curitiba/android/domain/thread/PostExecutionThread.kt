package br.curitiba.android.domain.thread

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}