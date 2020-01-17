package br.curitiba.android.clean.mobile_ui

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class TestRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)

        // Force all background thread run immediately
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    // Maker the runner redirects to use TestCleanArchApplication instead of CleanArchApplication class
    // when running integration tests
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestCleanArchApplication::class.java.name, context)
    }
}