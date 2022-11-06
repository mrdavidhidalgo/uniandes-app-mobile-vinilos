package com.team.vinylos.network

import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


object NetworkAdapter {

    private val albumResource: AlbumsResource = RetrofitHelper.getRetrofit().create(AlbumsResource::class.java)

    private val collectorResource: CollectorsResource = RetrofitHelper.getRetrofit().create(CollectorsResource::class.java)

    suspend fun getAlbums(): List<Album> = albumResource.getAlbums()

    suspend fun getCollectors(): List<Collector> = collectorResource.getCollectors()

}

object RetrofitHelper {
    fun getRetrofit(): Retrofit {

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl("https://vynils-back-heroku.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

interface AlbumsResource {
    @GET("/albums")
    suspend fun getAlbums():List<Album>
}

interface CollectorsResource {
    @GET("/collectors")
    suspend fun getCollectors():List<Collector>
}