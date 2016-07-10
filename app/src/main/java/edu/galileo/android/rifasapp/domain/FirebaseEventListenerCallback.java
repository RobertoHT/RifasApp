package edu.galileo.android.rifasapp.domain;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public interface FirebaseEventListenerCallback {
    void onChildAdded(DataSnapshot snapshot);
    void onChildRemoved(DataSnapshot snapshot);
    void onCancelled(FirebaseError error);
}
