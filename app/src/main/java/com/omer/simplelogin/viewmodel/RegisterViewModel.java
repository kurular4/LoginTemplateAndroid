package com.omer.simplelogin.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.omer.simplelogin.BR;
import com.omer.simplelogin.activity.LoginActivity;
import com.omer.simplelogin.constant.RegisterMessage;
import com.omer.simplelogin.model.dto.UserRegister;
import com.omer.simplelogin.repository.UserRepository;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;

public class RegisterViewModel extends AndroidViewModel implements Observable {
    private static RegisterViewModel instance;

    private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    private final UserRepository userRepository = UserRepository.getInstance();
    private UserRegister userRegister;

    @Bindable
    private String toastMessage = null;

    public RegisterViewModel(Application application) {
        super(application);
        userRegister = new UserRegister("", "", "");
    }

    public static RegisterViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new RegisterViewModel(application);
        }
        return instance;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUsername() {
        return userRegister.getUsername();
    }

    public void setUsername(String username) {
        userRegister.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return userRegister.getPassword();
    }

    public void setPassword(String password) {
        userRegister.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getEmail() {
        return userRegister.getEmail();
    }

    public void setEmail(String email) {
        userRegister.setEmail(email);
        notifyPropertyChanged(BR.email);
    }

    public void setLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void onRegisterClicked() {
        if (isInputDataValid()) {
            userRepository.register(userRegister);
        } else {
            setToastMessage(RegisterMessage.FAIL);
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
