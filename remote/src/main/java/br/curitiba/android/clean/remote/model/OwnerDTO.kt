package br.curitiba.android.clean.remote.model

import com.google.gson.annotations.SerializedName

data class OwnerDTO(
    @SerializedName("login") val ownerName: String,
    @SerializedName("avatar_url") val ownerAvatar: String
)