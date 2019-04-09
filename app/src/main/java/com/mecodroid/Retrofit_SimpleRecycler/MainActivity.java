package com.mecodroid.Retrofit_SimpleRecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    RecAdapter adapter;
    List<Item> getusers;
    ProgressBar progressBar;
    CheckInternet checkInternet;
    boolean b, a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInternet = new CheckInternet(MainActivity.this);
        a = checkInternet.Is_Connecting();
        if (!a) {
            Toast.makeText(MainActivity.this, "please check Internet Connection", Toast.LENGTH_LONG).show();
        } else {
            setuprec();
        }
    }


    private void setuprec() {
        progressBar = findViewById(R.id.bar1);

        rv = findViewById(R.id.rview);
        LinearLayoutManager lin = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(lin);
        rv.setHasFixedSize(true);
        fetchContact("ahmed");
    }

    private void fetchContact(String users) {
        Retrofit instance = RetrofitClient.getInstance();
        Users_API usersApi = instance.create(Users_API.class);
        Call<GithubUsers> usersCall = usersApi.getUsersInfo(users);

        usersCall.enqueue(new Callback<GithubUsers>() {
            @Override
            public void onResponse(Call<GithubUsers> call, Response<GithubUsers> response) {
                progressBar.setVisibility(View.GONE);
                getusers = new ArrayList<Item>();
                GithubUsers body = response.body();
                List<Item> items = body.getItems();
                getusers.addAll(items);
                adapter = new RecAdapter(getApplicationContext(), getusers);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GithubUsers> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setuprec();
    }

}
