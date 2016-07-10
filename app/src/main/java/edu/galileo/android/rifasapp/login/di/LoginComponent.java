package edu.galileo.android.rifasapp.login.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.rifasapp.RifasAppModule;
import edu.galileo.android.rifasapp.domain.di.DomainModule;
import edu.galileo.android.rifasapp.libs.di.LibsModule;
import edu.galileo.android.rifasapp.login.ui.LoginActivity;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, RifasAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
