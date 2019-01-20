package io.indrian16.testkoin.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import io.indrian16.testkoin.data.model.User
import io.indrian16.testkoin.util.Constant

@Database(entities = [User::class], version = Constant.DB_VER, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var mInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            if (mInstance == null) {

                mInstance = Room.databaseBuilder(context, AppDatabase::class.java, Constant.DB_NAME)
                        .build()
            }

            return mInstance!!
        }
    }
}