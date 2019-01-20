package io.indrian16.testkoin.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.indrian16.testkoin.util.Constant
import java.util.*

@Entity(tableName = Constant.TABLE_USER)
data class User(
    val name: String,
    val age: Int,
    @PrimaryKey val createAt: Date? = null
)