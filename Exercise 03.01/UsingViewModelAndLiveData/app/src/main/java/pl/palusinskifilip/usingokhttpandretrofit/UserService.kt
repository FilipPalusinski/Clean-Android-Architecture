package pl.palusinskifilip.usingokhttpandretrofit

import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getUsers(): List<User>
}
