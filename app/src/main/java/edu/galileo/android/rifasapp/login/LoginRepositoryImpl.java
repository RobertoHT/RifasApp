package edu.galileo.android.rifasapp.login;

import com.firebase.client.FirebaseError;

import edu.galileo.android.rifasapp.domain.FirebaseAPI;
import edu.galileo.android.rifasapp.domain.FirebaseActionListenerCallback;
import edu.galileo.android.rifasapp.libs.base.EventBus;
import edu.galileo.android.rifasapp.login.events.LoginEvent;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public class LoginRepositoryImpl implements LoginRepository {
    EventBus eventBus;
    FirebaseAPI firebase;

    public LoginRepositoryImpl(EventBus eventBus, FirebaseAPI firebase) {
        this.eventBus = eventBus;
        this.firebase = firebase;
    }

    @Override
    public void doSignUp(final String email, final String password) {
        firebase.signup(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(LoginEvent.onSignUpSuccess);
                doSignIn(email, password);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.onSignUpError, error.getMessage(), null);
            }
        });
    }

    @Override
    public void doSignIn(String email, String password) {
        if (email != null && password != null) {
            firebase.login(email, password, new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebase.getAuthEmail();
                    post(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    post(LoginEvent.onSignInError, error.getMessage(), null);
                }
            });
        } else {
            firebase.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebase.getAuthEmail();
                    post(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    post(LoginEvent.onFailedToRecoverSession);
                }
            });
        }
    }

    private void post(int type) {
        post(type, null, null);
    }

    private void post(int type, String currentUserEmail) {
        post(type, null, currentUserEmail);
    }

    private void post(int type, String errorMessage, String currentUserEmail) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setErrorMesage(errorMessage);
        loginEvent.setCurrentUserEmail(currentUserEmail);
        eventBus.post(loginEvent);
    }
}
