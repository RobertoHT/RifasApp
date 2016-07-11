package edu.galileo.android.rifasapp.main;

import edu.galileo.android.rifasapp.entities.Rifa;

/**
 * Created by praxis on 11/07/16.
 */
public interface MainInteractor {
    void subscribe();
    void unsubscribe();
    void logout();
    void createRifa(Rifa rifa);
    void removePhoto(Rifa rifa);
}
