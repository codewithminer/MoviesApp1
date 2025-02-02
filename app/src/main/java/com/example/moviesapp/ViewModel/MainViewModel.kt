package com.example.moviesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.Domain.FilmItemModel
import com.example.moviesapp.Repository.MainRepository

class MainViewModel: ViewModel() {

    private val repository: MainRepository = MainRepository()

    fun loadUpcoming(): LiveData<MutableList<FilmItemModel>>{
        return repository.loadUpcoming()
    }

    fun loadItems(): LiveData<MutableList<FilmItemModel>>{
        return repository.loadItems()
    }
}