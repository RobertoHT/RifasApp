package edu.galileo.android.rifasapp.domain.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.rifasapp.RifasApp;
import edu.galileo.android.rifasapp.RifasAppModule;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */
@Singleton
@Component(modules = {DomainModule.class, RifasAppModule.class})
public interface DomainComponent {
}
