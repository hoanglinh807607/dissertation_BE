package com.dissertation.common.context;

import com.dissertation.common.model.user_service.UserModel;

public class UserContext {

    private static final UserContext INSTANCE = new UserContext();

    private UserContext() {
        // Singleton implement
    }

    public static UserContext getInstance() {
        return INSTANCE;
    }

    private ThreadLocal<UserModel> threadLocal = new ThreadLocal<>();

    public UserModel getUserLogin() {
        UserModel userModel = threadLocal.get();
        return userModel;
    }

    public void setUserLogin(UserModel userModel) {
        threadLocal.set(userModel);
    }

    public void clearUser() {
        clearSession();
    }

    public void clearSession() {
        threadLocal.remove();
    }
}