package edu.galileo.android.rifasapp.libs.di;

import android.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.rifasapp.libs.GreenRobotEventBus;
import edu.galileo.android.rifasapp.libs.base.EventBus;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
