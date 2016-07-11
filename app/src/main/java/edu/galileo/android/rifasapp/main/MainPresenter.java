package edu.galileo.android.rifasapp.main;

import edu.galileo.android.rifasapp.entities.Rifa;
import edu.galileo.android.rifasapp.main.events.MainEvent;

/**
 * Created by praxis on 11/07/16.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();
    void subscribe();
    void unsubscribe();
    void logout();
    void createRifa(Rifa rifa);
    void removeRifa(Rifa rifa);
    void onEventMainThread(MainEvent event);
}
