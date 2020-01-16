package br.curitiba.android.clean.cache.mapper

interface CacheMapper<CACHE, DATA> {

    fun mapToData(cache: CACHE): DATA

    fun mapToCache(data: DATA): CACHE
}
