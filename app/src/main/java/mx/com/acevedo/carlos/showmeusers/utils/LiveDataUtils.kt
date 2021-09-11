package mx.com.acevedo.carlos.showmeusers.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * It parses a Mutable Live Data to Live Data
 */
fun <T> MutableLiveData<T>.toLiveData() = this as LiveData<T>