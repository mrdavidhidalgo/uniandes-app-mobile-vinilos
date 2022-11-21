package com.team.vinylos.network

import com.team.vinylos.models.Album
import com.team.vinylos.models.Artist
import com.team.vinylos.models.Collector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

import com.google.gson.JsonObject
import com.team.vinylos.models.AlbumResponse


object NetworkAdapter {

    private val albumResource: AlbumsResource = RetrofitHelper.getRetrofit().create(AlbumsResource::class.java)

    private val collectorResource: CollectorsResource = RetrofitHelper.getRetrofit().create(CollectorsResource::class.java)

    private val artistsResource: ArtistsResource = RetrofitHelper.getRetrofit().create(ArtistsResource::class.java)

    private val artistDetailResource: ArtistsDetailResource = RetrofitHelper.getRetrofit().create(ArtistsDetailResource::class.java)


    suspend fun getAlbums(): List<Album> = albumResource.getAlbums()

    suspend fun createAlbum(album : JsonObject): AlbumResponse = albumResource.createAlbum(album)

    suspend fun getCollectors(): List<Collector> = collectorResource.getCollectors()

    suspend fun getArtists(): List<Artist> = artistsResource.getArtists()

    suspend fun getArtist(id: Int): Artist = artistDetailResource.getArtist(id)
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
    suspend fun getAlbums(): List<Album>

    @POST("/albums")
    suspend fun createAlbum(@Body album: JsonObject): AlbumResponse

}


    interface CollectorsResource {
    @GET("/collectors")
    suspend fun getCollectors():List<Collector>
}

interface ArtistsResource {
    @GET("/musicians")
    suspend fun getArtists():List<Artist>
}

interface ArtistsDetailResource {
    @GET("/musicians/{id}")
    suspend fun getArtist(@Path("id") artistId: Int):Artist
}