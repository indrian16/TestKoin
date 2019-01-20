package io.indrian16.testkoin.data.remote

import io.indrian16.testkoin.data.model.User
import io.indrian16.testkoin.util.Constant

class UserGenerate {

    val nameArray = arrayListOf(

        "Putri",
        "Kevin",
        "Irene",
        "arief",
        "Intan",
        "ahmad",
        "aulia",
        "farel",
        "Annisa",
        "Angga",
        "maria",
        "Henry",
        "Nurul",
        "stanley"
    )

    private fun randomAge() = (Constant.MIN_AGE..Constant.MAX_AGE).random()

    fun getGenerateUser(): List<User> {

        val randomUserList = arrayListOf<User>()

        nameArray.shuffle()
        for (name in nameArray) {
            
            randomUserList.add(User(name, randomAge(), null))
        }
        return randomUserList
    }
}