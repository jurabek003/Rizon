package uz.turgunboyevjurabek.rizon.viewmodel

import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.repository.AppRepository
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient

object MyViewModelObjects {
    val appRepository = AppRepository(ApiClient.getApiService())
}