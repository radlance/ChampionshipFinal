package com.radlance.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create

class AppServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var appService: AppService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val json = Json { ignoreUnknownKeys = true }
        appService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build().create<AppService>()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun login_test(): Unit = runTest {
        val body = """
            {
                "token": "token1",
                "user": {
                    "id": "12345",
                    "email": "test@test.test",
                    "name": "name1"
                }
            }
        """.trimIndent()

        val response = MockResponse().setResponseCode(200).setBody(body)
        mockWebServer.enqueue(response)

        val userResponse = appService.login(UserLoginDto("test@test.test", "123456Aa!"))

        assertEquals("token1", userResponse.token)
        assertEquals("12345", userResponse.user.id)
        assertEquals("test@test.test", userResponse.user.email)
        assertEquals("name1", userResponse.user.name)

        val recorderRequest = mockWebServer.takeRequest()
        assertEquals("POST", recorderRequest.method)
        assertEquals("/auth/login", recorderRequest.path)
    }
}