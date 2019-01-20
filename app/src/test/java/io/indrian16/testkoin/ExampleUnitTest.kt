package io.indrian16.testkoin

import io.indrian16.testkoin.data.remote.UserGenerate
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun TestGenerateUser() {

        val userGenerate = UserGenerate()

        for (userData in userGenerate.getGenerateUser()) {

            println(userData)
        }
    }
}
