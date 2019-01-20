package io.indrian16.testkoin.repository

import com.github.ajalt.timberkt.d
import io.indrian16.testkoin.data.local.UserDao
import io.indrian16.testkoin.data.model.User
import io.reactivex.Single

class LocalRepository(private val userDao: UserDao) {

    fun getUsersList(): Single<List<User>> {

        return userDao.getUsers()
                .toObservable()
                .flatMapIterable { it }
                .doOnNext {

                    d { "[GET] from local --> ${it.name}" }
                }
                .toList()
    }

    fun addUser(user: User) {

        d { "[INSERT] user --> ${user.name}" }
        userDao.insertUser(user)
    }

    fun deleteUser(user: User) {

        d { "[DELETE] user --> ${user.name}" }
        userDao.deleteUser(user)
    }
}