package io.indrian16.testkoin.view.remote

import io.indrian16.testkoin.BasePresenter
import io.indrian16.testkoin.BaseView
import io.indrian16.testkoin.data.model.User

interface RemoteContract {

    interface View : BaseView<Presenter>{

        fun updateDataRv(userList: List<User>)

        fun showLoading()

        fun hideLoading()

        fun addUserInfo(name: String)

        fun showError(error: String)
    }

    interface Presenter : BasePresenter<View> {

        fun getUsers()

        fun addUser(user: User)

        fun unSubscribe()
    }
}