package io.indrian16.testkoin.repository

import io.indrian16.testkoin.data.model.User

class Repository(private val localRepository: LocalRepository,
                 private val remoteRepository: RemoteRepository) {

    fun getUsersFromLocal() = localRepository.getUsersList()

    fun getUsersFromRemote() = remoteRepository.getUsersList()

    fun addUserToDB(user: User) = localRepository.addUser(user)

    fun deleteUser(user: User) = localRepository.deleteUser(user)
}