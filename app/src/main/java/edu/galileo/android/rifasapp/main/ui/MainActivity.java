package edu.galileo.android.rifasapp.main.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import edu.galileo.android.rifasapp.R;
import edu.galileo.android.rifasapp.domain.FirebaseAPI;
import edu.galileo.android.rifasapp.entities.Rifa;
import edu.galileo.android.rifasapp.login.ui.LoginActivity;
import edu.galileo.android.rifasapp.main.MainPresenter;
import edu.galileo.android.rifasapp.main.ui.adapters.RifaListAdapter;

public class MainActivity extends AppCompatActivity implements MainView {
    @Inject
    MainPresenter presenter;
    @Inject
    RifaListAdapter adapter;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        presenter.logout();
        sharedPreferences.edit().clear().commit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onAddInit() {

    }

    @Override
    public void onAddComplete() {

    }

    @Override
    public void onAddError(String error) {

    }

    @Override
    public void showList() {

    }

    @Override
    public void hideList() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void addRifa(Rifa rifa) {

    }

    @Override
    public void removeRifa(Rifa rifa) {

    }

    @Override
    public void onRifaError(String error) {

    }
}
