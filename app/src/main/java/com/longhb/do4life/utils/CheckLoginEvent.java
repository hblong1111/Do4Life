package com.longhb.do4life.utils;

public interface CheckLoginEvent {
    void onLoginSuccess(String idAcc);
    void onLoginError();
}
