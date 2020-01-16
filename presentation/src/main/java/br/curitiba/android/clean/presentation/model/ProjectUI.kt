package br.curitiba.android.clean.presentation.model

data class ProjectUI(
    val id: String,
    val name: String,
    val fullName: String,
    val starCount: String,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean
)