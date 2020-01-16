package br.curitiba.android.clean.presentation.mapper

interface UIMapper<out UI, in D> {

    fun mapToView(domain: D): UI
}