package edu.galileo.android.rifasapp.main.ui;

import edu.galileo.android.rifasapp.entities.Rifa;

/**
 * Created by praxis on 11/07/16.
 */
public interface MainView {

    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void saveRifa(Rifa rifa);
    void addRifa(Rifa rifa);
    void removeRifa(Rifa rifa);
    void onRifaError(String error);
}
