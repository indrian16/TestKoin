package io.indrian16.testkoin.view.local

import com.github.ajalt.timberkt.d
import io.indrian16.testkoin.data.model.User
import io.indrian16.testkoin.repository.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LocalPresenter(private val repository: Repository) : LocalContract.Presenter {

    override lateinit var view: LocalContract.View
    private val compositeDisposable = CompositeDisposable()

    override fun getUsers() {

        val disposable = repository.getUsersFromLocal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onReceivedList, this::onError)

        compositeDisposable.add(disposable)
    }

    override fun deleteUser(user: User) {

        val disposable = Observable.fromCallable {

            repository.deleteUser(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view.showDeleteInfo(user.name)
                getUsers()

            }, this::onError)

        compositeDisposable.add(disposable)
    }

    private fun onReceivedList(userList: List<User>) {

        view.updateDataRv(userList)
    }

    private fun onError(throwable: Throwable) {

        val errorStr = throwable.message.toString()
        d { errorStr }
        view.showError(errorStr)
    }

    override fun unSubscribe() {

        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}