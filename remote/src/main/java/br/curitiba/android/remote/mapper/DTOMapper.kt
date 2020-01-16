package br.curitiba.android.remote.mapper

interface DTOMapper<in DTO, out DATA> {

    fun mapToData(dto: DTO): DATA
}