package com.example.timetonicapp.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetonicapp.SharedPreferencesManager
import com.example.timetonicapp.data.model.UserOAuthResult
import com.example.timetonicapp.data.model.UserSessionKeyResult
import com.example.timetonicapp.domain.LoginUseCase
import com.example.timetonicapp.domain.UserSessionKeyUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    context: Context
) : ViewModel() {

    private val loginUseCase = LoginUseCase()
    private val userSessionKeyUseCase = UserSessionKeyUseCase()
    private val sharedPreferences = SharedPreferencesManager(context)

    private val _oauthResult = MutableLiveData<UserOAuthResult>()
    private val oauthResult: LiveData<UserOAuthResult> = _oauthResult

    private val _userSessionKeyResult = MutableLiveData<UserSessionKeyResult>()
    private val userSessionKeyResult: LiveData<UserSessionKeyResult> = _userSessionKeyResult

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isEnabled = MutableLiveData<Boolean>()
    val isEnabled: LiveData<Boolean> = _isEnabled


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isEnabled.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onLoginClicked() {
        viewModelScope.launch {
            email.value?.let { email ->
                password.value?.let { password ->
                    _oauthResult.value = loginUseCase.invoke(
                        user = email,
                        password = password
                    )
                }
            }
        }

        oauthResult.value?.let { oauthResult ->
            if (oauthResult.status == "ok") {
                getSessionKey(
                    oU = oauthResult.oU,
                    uC = oauthResult.oU,
                    oauthKey = oauthResult.oauthKey
                )

            } else {
                print("No sirvio")
            }
        }
    }

    private fun getSessionKey(oU: String, uC: String, oauthKey: String) {
        viewModelScope.launch {
            _userSessionKeyResult.value = userSessionKeyUseCase.invoke(
                oU = oU,
                uC = uC,
                oauthKey = oauthKey
            )
        }
        userSessionKeyResult.value?.let { sessionKey ->
            if (sessionKey.status == "ok") sharedPreferences.saveKey(sessionKey.sessionKey)
        }
    }
}
