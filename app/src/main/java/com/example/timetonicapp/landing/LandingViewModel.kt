package com.example.timetonicapp.landing

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetonicapp.data.model.UserAllBooks
import com.example.timetonicapp.domain.UserBooksUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class LandingViewModel(
    oU: String,
    sessionKey: String,
    private val userBooksUseCase: UserBooksUseCase,
) : ViewModel() {

    private val _allBooks = MutableLiveData<UserAllBooks>()
    val allBooks: LiveData<UserAllBooks> = _allBooks

    val showContent = mutableStateOf(value = false)
    val progressBar = mutableStateOf(value = true)

    init {
        getAllBooks(oU, sessionKey)
    }

    private fun getAllBooks(oU: String, sessionKey: String) {
        viewModelScope.launch {
            try {
                _allBooks.value = userBooksUseCase.invoke(
                    oU = oU,
                    uC = oU,
                    sessionKey = sessionKey
                )
                allBooks.value?.let {
                    if (it.status == "ok") {
                        showContent.value = true
                        progressBar.value = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
            }
        }
    }
}