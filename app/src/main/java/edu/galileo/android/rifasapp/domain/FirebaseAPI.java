package edu.galileo.android.rifasapp.domain;

import android.support.annotation.NonNull;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

import edu.galileo.android.rifasapp.entities.Rifa;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public class FirebaseAPI {
    private Firebase firebase;
    private FirebaseAuth firebaseAuth;
    private ChildEventListener rifasEventListener;

    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void checkForData(final FirebaseActionListenerCallback listener){
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {
                    listener.onSuccess();
                } else {
                    listener.onError(null);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });
    }

    public void subscribe(final FirebaseEventListenerCallback listener) {
        if (rifasEventListener == null) {
            rifasEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listener.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listener.onChildRemoved(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    listener.onCancelled(firebaseError);
                }
            };
            firebase.addChildEventListener(rifasEventListener);
        }
    }

    public void unsubscribe() {
        firebase.removeEventListener(rifasEventListener);
    }

    public String create() {
        return firebase.push().getKey();
    }

    public void update(Rifa rifa) {
        Firebase reference = this.firebase.child(rifa.getId());
        reference.setValue(rifa);
    }

    public void remove(Rifa rifa, FirebaseActionListenerCallback listener) {
        firebase.child(rifa.getId()).removeValue();
        listener.onSuccess();
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
