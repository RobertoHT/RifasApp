package edu.galileo.android.rifasapp.main.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.rifasapp.RifasAppModule;
import edu.galileo.android.rifasapp.domain.di.DomainModule;
import edu.galileo.android.rifasapp.libs.di.LibsModule;
import edu.galileo.android.rifasapp.main.ui.MainActivity;

/**
 * Created by Roberto Hdez. on 11/07/16.
 */
@Singleton
@Component(modules = {MainModule.class, DomainModule.class, LibsModule.class, RifasAppModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
