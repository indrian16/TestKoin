package io.indrian16.testkoin.di

import android.arch.persistence.room.Room
import io.indrian16.testkoin.data.local.AppDatabase
import io.indrian16.testkoin.data.remote.UserGenerate
import io.indrian16.testkoin.repository.LocalRepository
import io.indrian16.testkoin.repository.RemoteRepository
import io.indrian16.testkoin.repository.Repository
import io.indrian16.testkoin.util.Constant
import io.indrian16.testkoin.view.local.LocalContract
import io.indrian16.testkoin.view.local.LocalPresenter
import io.indrian16.testkoin.view.remote.RemoteContract
import io.indrian16.testkoin.view.remote.RemotePresenter
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module

val appModule = applicationContext {

    bean { Room.databaseBuilder(get(), AppDatabase::class.java, Constant.DB_NAME).build() }
    bean { get<AppDatabase>().userDao() }

    factory { RemotePresenter(get()) as RemoteContract.Presenter}
    factory { LocalPresenter(get()) as LocalContract.Presenter}
}

val repoModule = module {

    single { LocalRepository(get()) }
    single { UserGenerate() }
    single { RemoteRepository(get()) }

    single { Repository(get(), get()) }
}

val listModule = listOf(appModule, repoModule)