package com.radlance.championshipfinal.presentation.common

import com.radlance.championshipfinal.domain.remote.FetchResult

class FetchResultMapper<T> : FetchResult.Mapper<T, FetchResultUiState<T>> {
    override fun mapSuccess(data: T): FetchResultUiState<T> {
        return FetchResultUiState.Success(data)
    }

    override fun mapError(data: T?): FetchResultUiState<T> {
        return FetchResultUiState.Error(data)
    }
}