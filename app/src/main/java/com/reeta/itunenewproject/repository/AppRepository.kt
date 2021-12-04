package com.reeta.itunenewproject.repository

import androidx.lifecycle.LiveData
import com.reeta.itunenewproject.database.APIClient
import com.reeta.itunenewproject.database.AppDao
import com.reeta.itunenewproject.database.ItunesDbTable
import com.reeta.itunenewproject.di.Module
import com.reeta.itunenewproject.response.ItunesResponseModel
import com.reeta.itunenewproject.response.Resource
import com.reeta.itunenewproject.response.ResponseHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepository @Inject constructor(private val appDao: AppDao, val apiClient: APIClient) {

    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun getDataFromAPI(query: String): Resource<ItunesResponseModel> {
        return try {
            val itunesResponseModel: ItunesResponseModel =
                Module.provideApiService().getResponse(query)
            responseHandler.handleSuccess(itunesResponseModel)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun insertDataInDb(itunesDbTable: ItunesDbTable) {
        CoroutineScope(Dispatchers.IO).launch {
            appDao.deleteAllDataFromDB()
            appDao.addDataFromAPI(itunesDbTable)
        }
    }

    fun getDataFromDb(): LiveData<ItunesDbTable> {
        return appDao.getResponseFromDb()
    }

    fun deleteDataFromDb(){
        appDao.deleteAllDataFromDB()
    }
}