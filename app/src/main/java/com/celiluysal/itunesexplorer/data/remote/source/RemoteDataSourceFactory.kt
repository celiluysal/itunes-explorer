package com.celiluysal.itunesexplorer.data.remote.source

import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.NO_INTERNET_CONNECTION
import com.celiluysal.itunesexplorer.data.model.UNAUTHORIZED
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import com.celiluysal.itunesexplorer.data.remote.service.ItunesService
import com.celiluysal.itunesexplorer.utils.NetworkUtil
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceFactory @Inject constructor(
    private val itunesService: ItunesService,
    private val networkUtil: NetworkUtil
) : RemoteDataSource {
    override suspend fun search(
        term: String?,
        limit: Int?,
        offset: Int?,
        entity: String?
    ): Resource<SearchResult> {
        itunesService.search(term, limit, offset, entity)
            .let {
                return handleResponse(it) as Resource<SearchResult>
            }
    }

    private fun handleResponse(response: Response<*>): Resource<Any> {
        if (!networkUtil.isNetworkAvailable()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION)
        }

        return try {
            when {
                response.isSuccessful -> Resource.Success(data = response.body())
                response.code() == UNAUTHORIZED -> {
                    //Could be customize
                    Resource.DataError(errorCode = response.code())
                }
                else -> Resource.DataError(errorCode = response.code())
            }
        } catch (e: IOException) {
            Resource.DataError(errorCode = NO_INTERNET_CONNECTION)
        }
    }
}