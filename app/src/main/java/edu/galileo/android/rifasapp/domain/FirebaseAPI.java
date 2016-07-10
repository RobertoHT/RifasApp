package edu.galileo.android.rifasapp.domain;

import android.support.annotation.NonNull;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public class FirebaseAPI {
    private Firebase firebase;
    private ChildEventListener rifasEventListener;
    private FirebaseAuth firebaseAuth;

    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void logout(){
        firebaseAuth.signOut();
    }

    public void login(String email, String password, final FirebaseActionListenerCallback listener){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else{
                    listener.onError(new FirebaseError(0,task.getException().toString()));
                }
            }
        });
    }

    public void signup(String email, String password, final FirebaseActionListenerCallback listener){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else{
                    listener.onError(new FirebaseError(0,task.getException().toString()));
                }
            }
        });
    }

    public void checkForSession(FirebaseActionListenerCallback listener) {
        if (firebaseAuth.getCurrentUser() != null) {
            listener.onSuccess();
        } else {
            listener.onError(null);
        }
    }

    public String getAuthEmail(){
        String email = null;
        if (firebaseAuth.getCurrentUser() != null) {
            email = firebaseAuth.getCurrentUser().getEmail();
        }
        return email;
    }
}
