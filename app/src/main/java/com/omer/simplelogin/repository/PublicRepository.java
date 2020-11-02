package com.omer.simplelogin.repository;

import android.content.Context;

import com.omer.simplelogin.api.service.PublicService;
import com.omer.simplelogin.api.Response;
import com.omer.simplelogin.api.RetrofitFactory;
import com.omer.simplelogin.model.User;
import com.omer.simplelogin.model.dto.UserLogin;
import com.omer.simplelogin.utils.SharedPreferencesUtils;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;

public class PublicRepository {
    private static volatile PublicRepository instance;

    private final PublicService publicService;
    private final MutableLiveData<User> user = new MutableLiveData<>();

    private PublicRepository() {
        publicService = RetrofitFactory.getRetrofit().create(PublicService.class);
    }

    public static PublicRepository getInstance() {
        if (instance == null) {
            instance = new PublicRepository();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user.getValue() != null;
    }

    public void logout(Context context) {
        user.setValue(null);
        SharedPreferencesUtils.deleteData(context, "token");
    }

    private void setLoggedInUser(User user) {
        this.user.setValue(user);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void login(final Context context, UserLogin userLogin) {
        publicService.login(userLogin).enqueue(new Callback<Response<User>>() {
            @Override
            public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
                if (response.isSuccessful()) {
                    SharedPreferencesUtils.saveData(context, "token", response.headers().get("token"));
                    if (response.body() != null) {
                        setLoggedInUser(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<User>> call, Throwable t) {
                setLoggedInUser(null);
            }
        });
    }
}
