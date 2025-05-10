package com.radlance.championshipfinal.domain.remote

interface FetchResult<D> {

    fun <Ui> map(mapper: Mapper<D, Ui>): Ui

    interface Mapper<D, Ui> {

        fun mapSuccess(data: D): Ui

        fun mapError(data: D?): Ui
    }

    class Success<D : Any>(private val data: D) : FetchResult<D> {
        override fun <Ui> map(mapper: Mapper<D, Ui>): Ui = mapper.mapSuccess(data)
    }

    class Error<D>(private val data: D?) : FetchResult<D> {
        override fun <Ui> map(mapper: Mapper<D, Ui>): Ui = mapper.mapError(data)
    }
}