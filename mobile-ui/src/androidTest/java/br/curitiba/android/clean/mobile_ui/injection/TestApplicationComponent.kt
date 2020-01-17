package br.curitiba.android.clean.mobile_ui.injection

import android.app.Application
import br.curitiba.android.clean.domain.repository.ProjectsRepository
import br.curitiba.android.clean.mobile_ui.TestCleanArchApplication
import br.curitiba.android.clean.mobile_ui.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestApplicationModule::class,
        TestDataModule::class,
        TestCacheModule::class,
        TestRemoteModule::class,
        MobileUIModule::class,
        PresentationModule::class
    ]
)
interface TestApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun inject(app: TestCleanArchApplication)

    // Mocks getters
    fun projectsRepository(): ProjectsRepository
}