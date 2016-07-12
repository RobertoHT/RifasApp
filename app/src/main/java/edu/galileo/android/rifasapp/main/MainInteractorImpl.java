package edu.galileo.android.rifasapp.main;

import edu.galileo.android.rifasapp.entities.Rifa;

/**
 * Created by praxis on 11/07/16.
 */
public class MainInteractorImpl implements MainInteractor {
    private MainRepository repository;

    public MainInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void logout() {
        repository.logout();
    }

    @Override
    public void createRifa(Rifa rifa) {
        repository.create(rifa);
    }

    @Override
    public void removePhoto(Rifa rifa) {
        repository.remove(rifa);
    }
}
