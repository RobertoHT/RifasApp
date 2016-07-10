package edu.galileo.android.rifasapp.login;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public interface LoginInteractor {
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
