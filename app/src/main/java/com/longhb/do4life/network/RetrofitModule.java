package com.longhb.do4life.network;

public class RetrofitModule {
    private static RetrofitService INSTANCE;

    private RetrofitModule() {
    }

    public static RetrofitService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = RetrofitClient.getInstance().create(RetrofitService.class);
        }
        return INSTANCE;
    }
}
