package br.curitiba.android.clean.presentation.resource

import androidx.lifecycle.MutableLiveData

// UIRes?
// UIJob?
// UIOperation?
// UITask?
data class Resource<T> constructor(
    val state: ResourceState? = null,
    val data: T? = null,
    val message: String? = null
)

fun <T> MutableLiveData<Resource<T>>.toLoading() {
    this.value = this.value?.copy(state = ResourceState.LOADING)
}

fun <T> MutableLiveData<Resource<T>>.toSucceeded(data: T?) {
    this.value = this.value?.copy(state = ResourceState.SUCCEEDED, data = data)
}

fun <T> MutableLiveData<Resource<T>>.toFailed(message: String?) {
    this.value = this.value?.copy(state = ResourceState.FAILED, message = message)
}