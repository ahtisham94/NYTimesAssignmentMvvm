package com.example.assignment.coreBase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


abstract class BaseRepository {

    /**
     * This method fetches the data from remoted service and emit data as flow to maintain API states
     */

    fun <T> baseApiResultHandler(call: suspend () -> Response<T>): Flow<APIState<Any>?> = flow {
        emit(APIState.ShowHideDialog(true))
        try {
            val response = call()
            if (response.isSuccessful) {
                emit(APIState.ShowHideDialog(false))
                emit(response.body()?.let { APIState.NetworkResponseSuccess(it) })
            } else {
                emit(APIState.ShowHideDialog(false))
                response.errorBody().let { error ->
                    error?.close()
                    emit(APIState.Error(error.toString()))
                }
            }

        } catch (e: Exception) {
            emit(APIState.ShowHideDialog(false))
            e.printStackTrace()
            emit(APIState.Error(e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)


}

enum class ApiCode(val codeOrDesc: String) {
    NO_INTERNET("no address associated")
}