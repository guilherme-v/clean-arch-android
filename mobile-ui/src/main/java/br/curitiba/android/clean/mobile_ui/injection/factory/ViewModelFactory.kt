package br.curitiba.android.clean.mobile_ui.injection.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
open class ViewModelFactory : ViewModelProvider.Factory {

    private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>

    @Suppress("ConvertSecondaryConstructorToPrimary", "unused")
    @Inject
    constructor(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) {
        this.creators = creators
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val creator = findProviderToViewModelClass(modelClass)
            ?: throw IllegalStateException("Unknown model class: $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun <T : ViewModel?> findProviderToViewModelClass(modelClass: Class<T>): Provider<out ViewModel>? =
        creators[modelClass]
}