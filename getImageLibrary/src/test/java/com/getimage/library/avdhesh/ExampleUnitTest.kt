package com.getimage.library.avdhesh

import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    private var apiInterface: APIInterface? = null

    @Test
    fun testGetImage_happyPath() {
        // Arrange
        apiInterface = APIClient.client!!.create(APIInterface::class.java)

        val actual  = apiInterface!!.doGetImage()
        actual.enqueue(object : Callback<Image> {
            override fun onResponse(call: Call<Image>, response: Response<Image>) {
                assertTrue(response.body()!!.message.isNotEmpty())
                assertTrue(containsUrl(response.body()!!.message))
            }

            override fun onFailure(call: Call<Image>, t: Throwable) {
                call.cancel()
            }
        })

    }
    fun containsUrl(input: String?): Boolean {
        return input != null && (input.contains("http://") || input.contains("https://"))
    }
}