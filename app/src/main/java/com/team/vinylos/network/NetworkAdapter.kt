package com.team.vinylos.network

import com.team.vinylos.models.Album
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object NetworkAdapter {

    private val albumResource: AlbumsResource = RetrofitHelper.getRetrofit().create(AlbumsResource::class.java)

    suspend fun getAlbums(): List<Album> = albumResource.getAlbums()

}

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://vynils-back-heroku.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

interface AlbumsResource {
    @GET("/albums")
    suspend fun getAlbums():List<Album>
}