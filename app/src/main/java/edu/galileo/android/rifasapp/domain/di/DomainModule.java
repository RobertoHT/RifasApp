package edu.galileo.android.rifasapp.domain.di;

import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.rifasapp.domain.FirebaseAPI;

/**
 * Created by Roberto Hdez. on 10/07/16.
 */
@Module
public class DomainModule {
    String firebaseURL;

    public DomainModule(String firebaseURL) {
        this.firebaseURL = firebaseURL;
    }

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(Firebase firebase){
        return new FirebaseAPI(firebase);
    }

    @Provides
    @Singleton
    Firebase providesFirebase(String firebaseURL){
        return new Firebase(firebaseURL);
    }

    @Provides
    @Singleton
    String providesFirebaseURL(){
        return this.firebaseURL;
    }
}
