package com.af.tutorialapp.retrofit.service;

import com.af.tutorialapp.retrofit.model.albums;
import com.af.tutorialapp.retrofit.model.user;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoints {

    @GET("albums")
    Call<List<albums>> getAlbums();

    //@Headers({""})
    @GET("albums/{id}")
    Call<albums> getAlbumsByid(@Path("id") int id);
    //@Query with post http

    @GET("albums")
    Observable<List<albums>> getAlbumsRx();

    @POST("posts")
    Call<user> postUser(@Body user user1);

    //query map used with hash map
    //body used with model
    //query with post parameter
    //get used with path
    //headers
    //header single header value


}
