package com.omer.simplelogin.api.service;

import com.omer.simplelogin.api.Response;
import com.omer.simplelogin.model.User;
import com.omer.simplelogin.model.dto.UserLogin;
import com.omer.simplelogin.model.dto.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @Headers("Content-Type: application/json")
    @PUT("user/create")
    Call<Response<User>> register(@Body UserRegister userRegister);
}
