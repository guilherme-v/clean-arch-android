package br.curitiba.android.clean.remote.mapper

interface DTOMapper<in DTO, out DATA> {

    fun mapToData(dto: DTO): DATA
}