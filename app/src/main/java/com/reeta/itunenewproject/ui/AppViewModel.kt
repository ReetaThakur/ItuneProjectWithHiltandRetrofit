package com.reeta.itunenewproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.reeta.itunenewproject.database.ItunesDbTable
import com.reeta.itunenewproject.repository.AppRepository
import com.reeta.itunenewproject.response.ItunesResponseModel
import com.reeta.itunenewproject.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    fun getDataFromAPI(query: String): LiveData<Resource<ItunesResponseModel>> {
        return liveData(Dispatchers.IO) {
            val data = appRepository.getDataFromAPI(query)
            emit(data)
        }
    }

    fun insertDataInDb(itunesDbTable: ItunesDbTable) {
        appRepository.insertDataInDb(itunesDbTable)
    }

    fun deleteDataFromDb() {
        appRepository.deleteDataFromDb()
    }

    fun getDataFromDb() {
        appRepository.getDataFromDb()
    }

}