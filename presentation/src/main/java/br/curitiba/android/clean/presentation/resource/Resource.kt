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
    val updatedValue = Resource(
        state = ResourceState.LOADING,
        data = this.value?.data,
        message = ""
    )
    this.postValue(updatedValue)
}

fun <T> MutableLiveData<Resource<T>>.toSucceeded(data: T?) {
    val updatedValue = Resource(
        state = ResourceState.SUCCEEDED,
        data = data,
        message = ""
    )
    this.postValue(updatedValue)
}

fun <T> MutableLiveData<Resource<T>>.toFailed(message: String?) {
    val updatedValue = Resource(
        state = ResourceState.FAILED,
        data = this.value?.data,
        message = message
    )
    this.postValue(updatedValue)
}