package edu.galileo.android.rifasapp;

import android.app.Application;

import com.firebase.client.Firebase;

import edu.galileo.android.rifasapp.domain.di.DomainModule;
import edu.galileo.android.rifasapp.libs.di.LibsModule;
import edu.galileo.android.rifasapp.login.di.DaggerLoginComponent;
import edu.galileo.android.rifasapp.login.di.LoginComponent;
import edu.galileo.android.rifasapp.login.di.LoginModule;
import edu.galileo.android.rifasapp.login.ui.LoginView;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */

public class RifasApp extends Application {
    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";
    private final static String FIREBASE_URL = "https://rifas-603bf.firebaseio.com/";

    private DomainModule domainModule;
    private RifasAppModule rifasAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initModules() {
        rifasAppModule = new RifasAppModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    public String getEmailKey() {
        return EMAIL_KEY;
    }

    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent
                .builder()
                .rifasAppModule(rifasAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();
    }
}
