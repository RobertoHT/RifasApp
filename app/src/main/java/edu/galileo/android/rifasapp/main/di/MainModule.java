package edu.galileo.android.rifasapp.main.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.rifasapp.domain.FirebaseAPI;
import edu.galileo.android.rifasapp.entities.Rifa;
import edu.galileo.android.rifasapp.libs.base.EventBus;
import edu.galileo.android.rifasapp.main.MainInteractor;
import edu.galileo.android.rifasapp.main.MainInteractorImpl;
import edu.galileo.android.rifasapp.main.MainPresenter;
import edu.galileo.android.rifasapp.main.MainPresenterImpl;
import edu.galileo.android.rifasapp.main.MainRepository;
import edu.galileo.android.rifasapp.main.MainRepositoryImpl;
import edu.galileo.android.rifasapp.main.ui.MainView;
import edu.galileo.android.rifasapp.main.ui.adapters.OnItemClickListener;
import edu.galileo.android.rifasapp.main.ui.adapters.RifaListAdapter;

/**
 * Created by Roberto Hdez. on 11/07/16.
 */
@Module
public class MainModule {
    MainView view;
    OnItemClickListener onItemClickListener;

    public MainModule(MainView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    MainView providesMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(EventBus eventBus, MainView view, MainInteractor interactor){
        return new MainPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    MainInteractor providesMainInteractor(MainRepository repository){
        return new MainInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, FirebaseAPI firebaseAPI){
        return new MainRepositoryImpl(eventBus, firebaseAPI);
    }

    @Provides
    @Singleton
    RifaListAdapter providesRifaAdapter(List<Rifa> rifaList, OnItemClickListener onItemClickListener){
        return new RifaListAdapter(rifaList, onItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Rifa> providesRifaList(){
        return new ArrayList<Rifa>();
    }
}
