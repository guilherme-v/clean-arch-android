package br.curitiba.android.clean.data.mapper

interface DataMapper<DT, D> {

    fun mapToDomain(data: DT): D

    fun mapToData(domain: D): DT
}

