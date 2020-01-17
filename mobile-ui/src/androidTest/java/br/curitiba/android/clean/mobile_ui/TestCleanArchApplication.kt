package br.curitiba.android.clean.mobile_ui

import android.app.Application
import androidx.test.InstrumentationRegistry
import br.curitiba.android.clean.mobile_ui.injection.DaggerTestApplicationComponent
import br.curitiba.android.clean.mobile_ui.injection.TestApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestCleanArchApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var appComponent: TestApplicationComponent

    companion object {
        @Suppress("DEPRECATION")
        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext as
                    TestCleanArchApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerTestApplicationComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }

    override fun androidInjector() = androidInjector
}