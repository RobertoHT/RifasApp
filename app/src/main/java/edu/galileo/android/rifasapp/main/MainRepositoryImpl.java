package edu.galileo.android.rifasapp.main;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import edu.galileo.android.rifasapp.domain.FirebaseAPI;
import edu.galileo.android.rifasapp.domain.FirebaseActionListenerCallback;
import edu.galileo.android.rifasapp.domain.FirebaseEventListenerCallback;
import edu.galileo.android.rifasapp.entities.Rifa;
import edu.galileo.android.rifasapp.libs.base.EventBus;
import edu.galileo.android.rifasapp.main.events.MainEvent;

/**
 * Created by praxis on 11/07/16.
 */
public class MainRepositoryImpl implements MainRepository {
    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public MainRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(FirebaseError error) {
                if(error != null){
                    post(MainEvent.READ_EVENT, error.getMessage());
                }else{
                    post(MainEvent.DELETE_EVENT, "");
                }
            }
        });

        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot snapshot) {
                Rifa rifa = snapshot.getValue(Rifa.class);
                rifa.setId(snapshot.getKey());
                post(MainEvent.READ_EVENT, rifa);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Rifa rifa = snapshot.getValue(Rifa.class);
                rifa.setId(snapshot.getKey());

                post(MainEvent.DELETE_EVENT, rifa);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(MainEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }

    @Override
    public void logout() {
        firebaseAPI.logout();
    }

    @Override
    public void create(Rifa rifa) {
        final String newRifaId = firebaseAPI.create();
        rifa.setId(newRifaId);
        firebaseAPI.update(rifa);
        post(MainEvent.SAVE_EVENT);
    }

    @Override
    public void remove(final Rifa rifa) {
        firebaseAPI.remove(rifa, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(MainEvent.DELETE_EVENT, rifa);
            }

            @Override
            public void onError(FirebaseError error) {
                post(MainEvent.DELETE_EVENT, error.getMessage());
            }
        });
    }

    private void post(int type){
        post(type, null, null);
    }

    private void post(int type, Rifa rifa){
        post(type, null, rifa);
    }

    private void post(int type, String error){
        post(type, error, null);
    }

    private void post(int type, String error, Rifa rifa){
        MainEvent event = new MainEvent();
        event.setType(type);
        event.setError(error);
        event.setRifa(rifa);
        eventBus.post(event);
    }
}
