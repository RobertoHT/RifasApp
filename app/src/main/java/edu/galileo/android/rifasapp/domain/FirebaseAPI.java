package edu.galileo.android.rifasapp.domain;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public class FirebaseAPI {
    private Firebase firebase;
    private ChildEventListener rifasEventListener;

    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
    }

    public void logout(){
        firebase.unauth();
    }

    public void login(String email, String password, final FirebaseActionListenerCallback listener){
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listener.onSuccess();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });
    }

    public void signup(String email, String password, final FirebaseActionListenerCallback listener){
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                listener.onSuccess();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });
    }

    public void checkForSession(FirebaseActionListenerCallback listener) {
        if (firebase.getAuth() != null) {
            listener.onSuccess();
        } else {
            listener.onError(null);
        }
    }

    public String getAuthEmail(){
        String email = null;
        if (firebase.getAuth() != null) {
            Map<String, Object> providerData = firebase.getAuth().getProviderData();
            email = providerData.get("email").toString();
        }
        return email;
    }
}
