package edu.galileo.android.rifasapp.main.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.rifasapp.R;
import edu.galileo.android.rifasapp.RifasApp;
import edu.galileo.android.rifasapp.entities.Rifa;
import edu.galileo.android.rifasapp.login.ui.LoginActivity;
import edu.galileo.android.rifasapp.main.MainPresenter;
import edu.galileo.android.rifasapp.main.ui.adapters.OnItemClickListener;
import edu.galileo.android.rifasapp.main.ui.adapters.RifaListAdapter;

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.plus)
    FloatingActionButton plus;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    MainPresenter presenter;
    @Inject
    RifaListAdapter adapter;
    @Inject
    SharedPreferences sharedPreferences;

    private RifasApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (RifasApp) getApplication();
        setupInjection();
        setupNavigation();

        presenter.onCreate();
        presenter.subscribe();
    }

    private void setupNavigation() {
        String email = sharedPreferences.getString(app.getEmailKey(), getString(R.string.app_name));
        toolbar.setTitle(email);
        setSupportActionBar(toolbar);
    }

    private void setupInjection() {
        app.getMainComponent(this, this).inject(this);
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

    @OnClick(R.id.plus)
    public void createRifa(){
        Log.d("TAG - RIFA: ", "Prueba");
    }

    @Override
    public void showList() {
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        list.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void saveRifa(Rifa rifa) {
        adapter.setRifa(rifa);
    }

    @Override
    public void addRifa(Rifa rifa) {
        adapter.setRifa(rifa);
    }

    @Override
    public void removeRifa(Rifa rifa) {
        adapter.removeRifa(rifa);
    }

    @Override
    public void onRifaError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemCLick(Rifa rifa) {
        Log.d("ITEM CLICK: ","prueba");
    }

    @Override
    public void onDeleteClick(Rifa rifa) {
        Log.d("DELETE CLICK: ","prueba");
    }
}
