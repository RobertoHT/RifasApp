package edu.galileo.android.rifasapp.domain;

import com.firebase.client.FirebaseError;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(FirebaseError error);
}
