package io.indrian16.testkoin.view.local

import io.indrian16.testkoin.data.model.User

interface LocalContract {

    interface View {

        fun updateDataRv(userList: List<User>)

        fun showDeleteInfo(name: String)

        fun showError(error: String)
    }

    interface Presenter {

        fun getUsers()

        fun deleteUser(user: User)

        fun unSubscribe()

    }
}