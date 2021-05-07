package com.monsun.suiicao.views.login;

public interface ILoginHandler {
    void HandlerError(Throwable throwable);
    boolean login();
    void startMainActivity();
    void showToast(CharSequence message);
    void setIsLoading(Boolean isLoading);


}
