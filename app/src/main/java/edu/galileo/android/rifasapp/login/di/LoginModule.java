package edu.galileo.android.rifasapp.login.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.rifasapp.domain.FirebaseAPI;
import edu.galileo.android.rifasapp.libs.base.EventBus;
import edu.galileo.android.rifasapp.login.LoginInteractor;
import edu.galileo.android.rifasapp.login.LoginInteractorImpl;
import edu.galileo.android.rifasapp.login.LoginPresenter;
import edu.galileo.android.rifasapp.login.LoginPresenterImpl;
import edu.galileo.android.rifasapp.login.LoginRepository;
import edu.galileo.android.rifasapp.login.LoginRepositoryImpl;
import edu.galileo.android.rifasapp.login.ui.LoginView;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */
@Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView() {
        return this.view;
    }

    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor) {
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor);
    }

    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides
    @Singleton
    LoginRepository providesLoginRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new LoginRepositoryImpl(eventBus, firebase);
    }
}
