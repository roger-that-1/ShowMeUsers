package mx.com.acevedo.carlos.showmeusers.utils

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Extension to configure schedulers on UI thread
 */
fun <T> Single<T>.applySchedulers(): Single<T> = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

/**
 * Extension to configure schedulers con IO thread
 */
fun <T> Single<T>.applySchedulersOnIo(): Single<T> = subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

/**
 * Extension to configure schedulers UI thread
 */
fun Completable.applySchedulers(): Completable = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

/**
 * Extension to configure schedulers on IO thread
 */
fun Completable.applySchedulersOnIo(): Completable = subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())