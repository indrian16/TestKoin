package io.indrian16.testkoin.view.remote

import io.indrian16.testkoin.data.model.User

interface RemoteContract {

    interface View {

        fun updateDataRv(userList: List<User>)

        fun showLoading()

        fun hideLoading()

        fun addUserInfo(name: String)

        fun showError(error: String)
    }

    interface Presenter {

        fun getUsers()

        fun addUser(user: User)

        fun unSubscribe()
    }
}