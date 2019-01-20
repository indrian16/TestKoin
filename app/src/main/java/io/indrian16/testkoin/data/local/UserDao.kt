package io.indrian16.testkoin.data.local

import android.arch.persistence.room.*
import io.indrian16.testkoin.data.model.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY createAt DESC")
    fun getUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}