package com.longhb.do4life.utils;

import com.longhb.do4life.model.retrofit.res.MyAccount;

public interface CheckLoginEvent {
    void onLoginSuccess(MyAccount myAccount);
    void onLoginError();
}
