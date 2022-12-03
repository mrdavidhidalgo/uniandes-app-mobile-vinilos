package com.team.vinylos.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

import com.google.gson.JsonObject
import com.team.vinylos.models.*


object NetworkAdapter {

    private val albumResource: AlbumsResource = RetrofitHelper.getRetrofit().create(AlbumsResource::class.java)

    private val albumCommentResource : AlbumsCommentsResource = RetrofitHelper.getRetrofit().create(AlbumsCommentsResource::class.java)

    private val collectorResource: CollectorsResource = RetrofitHelper.getRetrofit().create(CollectorsResource::class.java)

    private val getCollectorResource: GetCollectorsResource = RetrofitHelper.getRetrofit().create(GetCollectorsResource::class.java)

    private val artistResource: ArtistsResource = RetrofitHelper.getRetrofit().create(ArtistsResource::class.java)
	
    private val artistDetailResource: ArtistsDetailResource = RetrofitHelper.getRetrofit().create(ArtistsDetailResource::class.java)

    private val prizeResource: PrizeResource = RetrofitHelper.getRetrofit().create(PrizeResource::class.java)
    private val albumsByCollectorsResource: GetAlbumsByCollectorResource = RetrofitHelper.getRetrofit().create(GetAlbumsByCollectorResource::class.java)



    suspend fun getAlbums(): List<Album> = albumResource.getAlbums()

    suspend fun getAlbum(albumId:Integer): Album = albumResource.getAlbum(albumId)

    suspend fun createAlbum(album : JsonObject): AlbumResponse = albumResource.createAlbum(album)

    suspend fun getCollectors(): List<Collector> = collectorResource.getCollectors()

    suspend fun getCollector(collectorId:Integer): Collector = getCollectorResource.getCollector(collectorId)

    suspend fun getArtists(): List<Artist> = artistResource.getArtists()

    suspend fun getAlbumsByCollector(collectorId:Integer): List<CollectorAlbum> = albumsByCollectorsResource.getAlbums(collectorId)

    suspend fun getArtist(id: Int): Artist = artistDetailResource.getArtist(id)

    suspend fun getPrizes(): List<Prize> = prizeResource.getPrizes()

    suspend fun createPrize(prize: JsonObject): PrizeResponse = prizeResource.createPrize(prize)

    suspend fun createAlbumComment(albumId: Integer, comment: JsonObject): AlbumCommentResponse = albumCommentResource.createComment(albumId, comment)

}

object RetrofitHelper {
    fun getRetrofit(): Retrofit {

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://vinyls-team4.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

interface AlbumsResource {
    @GET("/albums")
    suspend fun getAlbums(): List<Album>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id") albumId:Integer ): Album

    @POST("/albums")
    suspend fun createAlbum(@Body album: JsonObject): AlbumResponse

}

interface AlbumsCommentsResource {
    @POST("/albums/{id}/comments")
    suspend fun createComment(@Path("id") albumId:Integer , @Body comment: JsonObject): AlbumCommentResponse
}


interface CollectorsResource {
    @GET("/collectors")
    suspend fun getCollectors():List<Collector>
}

interface ArtistsResource {
    @GET("/musicians")
    suspend fun getArtists():List<Artist>
}


interface GetCollectorsResource {
    @GET("/collectors/{id}")
    suspend fun getCollector(@Path("id") collectorId:Integer ): Collector
}

interface GetAlbumsByCollectorResource {
    @GET("/collectors/{id}/albums")
    suspend fun getAlbums(@Path("id") collectorId:Integer ): List<CollectorAlbum>
}

interface ArtistsDetailResource {
    @GET("/musicians/{id}")
    suspend fun getArtist(@Path("id") artistId: Int):Artist
}

interface  PrizeResource {
    @GET("/prizes")
    suspend fun getPrizes(): List<Prize>

    @POST("/prizes")
    suspend fun createPrize(@Body prize: JsonObject): PrizeResponse
}
