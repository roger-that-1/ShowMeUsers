package mx.com.acevedo.carlos.showmeusers.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import mx.com.acevedo.carlos.showmeusers.utils.SingleLiveEvent
import mx.com.acevedo.carlos.showmeusers.utils.toLiveData

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    protected val showError = SingleLiveEvent<String>()

    fun showError() = showError.toLiveData()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}