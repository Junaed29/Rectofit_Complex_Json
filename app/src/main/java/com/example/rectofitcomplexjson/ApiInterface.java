package com.example.rectofitcomplexjson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET("todos")
    Call<List<Todo>> getTodos();


    @POST("todos")
    Call<Todo> postTodo(@Body Todo todo);

    @GET
    Call<Employees> getAllEmployee(@Url String url);
}
