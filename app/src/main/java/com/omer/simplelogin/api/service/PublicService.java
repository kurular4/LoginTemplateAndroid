package com.omer.simplelogin.api.service;

import com.omer.simplelogin.api.Response;
import com.omer.simplelogin.model.User;
import com.omer.simplelogin.model.dto.UserLogin;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PublicService {

    @Headers("Content-Type: application/json")
    @POST("signin")
    Call<Response<User>> login(@Body UserLogin userLogin);
}
