package com.omer.simplelogin.repository;

import com.omer.simplelogin.api.Response;
import com.omer.simplelogin.api.RetrofitFactory;
import com.omer.simplelogin.api.service.UserService;
import com.omer.simplelogin.model.User;
import com.omer.simplelogin.model.dto.UserRegister;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;

public class UserRepository {
    private static volatile UserRepository instance;

    private final UserService userService;
    private final MutableLiveData<Boolean> isRegisterSuccessful = new MutableLiveData<>();

    public UserRepository() {
        userService = RetrofitFactory.getRetrofit().create(UserService.class);
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public MutableLiveData<Boolean> isRegisterSuccessful() {
        return isRegisterSuccessful;
    }

    public void register(UserRegister userRegister) {
        userService.register(userRegister).enqueue(new Callback<Response<User>>() {
            @Override
            public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        isRegisterSuccessful.setValue(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<User>> call, Throwable t) {
                isRegisterSuccessful.setValue(false);
            }
        });
    }
}
