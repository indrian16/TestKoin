package io.indrian16.testkoin.view.remote

import com.github.ajalt.timberkt.d
import io.indrian16.testkoin.data.model.User
import io.indrian16.testkoin.repository.Repository
import io.indrian16.testkoin.util.Constant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RemotePresenter(private val repository: Repository) : RemoteContract.Presenter {

    override lateinit var view: RemoteContract.View
    private val compositeDisposable = CompositeDisposable()

    override fun getUsers() {

        val disposable = repository.getUsersFromRemote()
                .toObservable()
                .delay(Constant.DELAY_GET_USERS, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribe(this::onReceivedList, this::onError)

        compositeDisposable.add(disposable)
    }

    override fun addUser(user: User) {

        val disposable = Observable.fromCallable {

            repository.addUserToDB(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view.addUserInfo(user.name)

            }, this::onError)

        compositeDisposable.add(disposable)
    }

    private fun onReceivedList(userList: List<User>) {

        view.updateDataRv(userList)
    }

    private fun onError(throwable: Throwable) {

        d { throwable.message.toString() }
        view.showError(throwable.message.toString())
    }

    override fun unSubscribe() {

        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}