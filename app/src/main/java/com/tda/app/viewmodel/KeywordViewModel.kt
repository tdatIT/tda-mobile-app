package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.KeywordRepository
import com.tda.app.model.Keyword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(val repository: KeywordRepository) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Keyword>())
    val keywords: StateFlow<List<Keyword>> = _state

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch {
            val data = repository.getAll()
            data?.let {
                _state.value = it
            }
        }
    }

    fun insert(keyword: String) {
        viewModelScope.launch {
            val newKw = Keyword(keyword = keyword)
            repository.insertKeyword(newKw)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            repository.deleteKey(id)
        }
    }

}