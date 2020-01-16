package br.curitiba.android.clean.cache.model.config

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.curitiba.android.clean.cache.db.AppDatabase
import br.curitiba.android.clean.cache.fake.FakeFactory
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ConfigCacheDaoTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: AppDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun `it should get Configs into db`() {
        // arrange
        val config = FakeFactory.makeConfigCache()
        database.configDao().insertConfig(config)

        // act
        val testObserver = database.configDao().getConfig().test()

        // assert
        testObserver.assertValue(config)
    }
}