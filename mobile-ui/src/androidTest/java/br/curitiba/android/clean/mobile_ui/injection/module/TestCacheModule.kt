package br.curitiba.android.clean.mobile_ui.injection.module

import android.app.Application
import br.curitiba.android.clean.cache.ProjectsCacheImpl
import br.curitiba.android.clean.cache.db.AppDatabase
import br.curitiba.android.clean.data.cache.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.mockk.mockk

@Module
abstract class TestCacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }

        @Provides
        fun bindProjectsRepository(): ProjectsCache = mockk()
    }
}
