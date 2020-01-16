package br.curitiba.android.clean.domain.thread

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}