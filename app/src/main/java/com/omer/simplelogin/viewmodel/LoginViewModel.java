package com.omer.simplelogin.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.omer.simplelogin.BR;
import com.omer.simplelogin.activity.RegisterActivity;
import com.omer.simplelogin.constant.LoginMessage;
import com.omer.simplelogin.model.dto.UserLogin;
import com.omer.simplelogin.repository.PublicRepository;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;

public class LoginViewModel extends AndroidViewModel implements Observable {
    private static LoginViewModel instance;

    private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    private final PublicRepository publicRepository = PublicRepository.getInstance();
    private final UserLogin userLogin;

    @Bindable
    private String toastMessage = null;

    private LoginViewModel(Application application) {
        super(application);
        userLogin = new UserLogin("", "");
    }

    public static LoginViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new LoginViewModel(application);
        }
        return instance;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUsername() {
        return userLogin.getUsername();
    }

    public void setUsername(String username) {
        userLogin.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return userLogin.getPassword();
    }

    public void setPassword(String password) {
        userLogin.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    public void setRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public void onLoginClicked() {
        if (isInputDataValid()) {
            publicRepository.login(getApplication().getApplicationContext(), userLogin);
        } else {
            setToastMessage(LoginMessage.FAIL);
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUsername()) && getPassword().length() > 2;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }
}
