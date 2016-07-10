package edu.galileo.android.rifasapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */
@Module
public class RifasAppModule {
    RifasApp app;

    public RifasAppModule(RifasApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application){
        return application.getSharedPreferences(app.getSharedPreferencesName(), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return this.app;
    }
}
