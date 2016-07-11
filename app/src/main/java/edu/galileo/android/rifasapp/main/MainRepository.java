package edu.galileo.android.rifasapp.main;

import edu.galileo.android.rifasapp.entities.Rifa;

/**
 * Created by praxis on 11/07/16.
 */
public interface MainRepository {
    void subscribe();
    void unsubscribe();
    void logout();
    void create(Rifa rifa);
    void remove(Rifa rifa);
}
