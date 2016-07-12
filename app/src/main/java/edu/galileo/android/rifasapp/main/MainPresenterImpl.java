package edu.galileo.android.rifasapp.main;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.rifasapp.entities.Rifa;
import edu.galileo.android.rifasapp.libs.base.EventBus;
import edu.galileo.android.rifasapp.main.events.MainEvent;
import edu.galileo.android.rifasapp.main.ui.MainView;

/**
 * Created by praxis on 11/07/16.
 */
public class MainPresenterImpl implements MainPresenter {
    EventBus eventBus;
    MainView view;
    MainInteractor interactor;
    static final String EMPTY_LIST = "Listado vacío";

    public MainPresenterImpl(EventBus eventBus, MainView view, MainInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void subscribe() {
        if(view != null){
            view.hideList();
            view.showProgress();
        }
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();
    }

    @Override
    public void logout() {
        view = null;
        interactor.logout();
    }

    @Override
    public void createRifa(Rifa rifa) {
        interactor.createRifa(rifa);
    }

    @Override
    public void removeRifa(Rifa rifa) {
        interactor.removePhoto(rifa);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onRifaError(EMPTY_LIST);
                } else {
                    view.onRifaError(error);
                }
            } else {
                switch (event.getType()){
                    case MainEvent.SAVE_EVENT:
                        view.saveRifa(event.getRifa());
                        break;
                    case MainEvent.READ_EVENT:
                        view.addRifa(event.getRifa());
                        break;
                    case MainEvent.DELETE_EVENT:
                        view.removeRifa(event.getRifa());
                        break;
                }
            }
        }
    }
}
