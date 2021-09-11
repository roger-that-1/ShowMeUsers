package mx.com.acevedo.carlos.showmeusers.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import mx.com.acevedo.carlos.showmeusers.utils.toLiveData

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    protected val showError = MutableLiveData<String>()

    fun showError() = showError.toLiveData()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}