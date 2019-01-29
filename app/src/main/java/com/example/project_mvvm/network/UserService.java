package com.example.project_mvvm.network;


import com.example.project_mvvm.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("api/")
    Call<ResponseModel> getUsers();

}