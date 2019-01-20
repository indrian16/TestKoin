package io.indrian16.testkoin.repository

import com.github.ajalt.timberkt.d
import io.indrian16.testkoin.data.model.User
import io.indrian16.testkoin.data.remote.UserGenerate
import io.reactivex.Single

class RemoteRepository(private val userGenerate: UserGenerate) {

    fun getUsersList(): Single<List<User>> {

        return Single.just(userGenerate.getGenerateUser())
                .toObservable()
                .flatMapIterable { it }
                .doOnNext {

                    d { "[GET] from remote --> ${it.name}" }
                }
                .toList()
    }
}