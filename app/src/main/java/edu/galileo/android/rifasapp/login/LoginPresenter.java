package edu.galileo.android.rifasapp.login;

import edu.galileo.android.rifasapp.login.events.LoginEvent;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
