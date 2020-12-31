package com.longhb.do4life.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.longhb.do4life.viewmodel.ConfirmAccountViewModel;
import com.longhb.do4life.viewmodel.HomeFragmentViewModel;
import com.longhb.do4life.viewmodel.LoginViewModel;
import com.longhb.do4life.viewmodel.RegisterViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel();
        } else if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
            return (T) new HomeFragmentViewModel();
        }else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel();
        }else if (modelClass.isAssignableFrom(ConfirmAccountViewModel.class)) {
            return (T) new ConfirmAccountViewModel();
        }
        return null;
    }
}
