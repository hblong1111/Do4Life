package com.longhb.do4life.utils;

import com.longhb.do4life.model.MyAccount;

public interface CheckLoginEvent {
    void onLoginSuccess(MyAccount myAccount);
    void onLoginError();
}
