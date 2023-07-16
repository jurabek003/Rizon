package uz.turgunboyevjurabek.rizon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.repository.AppRepository

class MyViewModelFactory (val appRepository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AppViewModel::class.java)){
            return AppViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Error")
    }
}