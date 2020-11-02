package com.omer.simplelogin.viewmodel.factory;

import android.app.Application;

import com.omer.simplelogin.viewmodel.LoginViewModel;
import com.omer.simplelogin.viewmodel.MainViewModel;
import com.omer.simplelogin.viewmodel.RegisterViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @NonNull
    private final Application application;

    public ViewModelFactory(@NonNull Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == LoginViewModel.class) {
            return (T) LoginViewModel.getInstance(application);
        } else if (modelClass == RegisterViewModel.class) {
            return (T) RegisterViewModel.getInstance(application);
        } else if (modelClass == MainViewModel.class) {
            return (T) MainViewModel.getInstance(application);
        }
        return null;
    }
}
