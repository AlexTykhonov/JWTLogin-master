package com.example.semen.jwtlogin;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Toast;

import com.example.semen.jwtlogin.adapter.PetAdapter;
import com.example.semen.jwtlogin.api.Api;
import com.example.semen.jwtlogin.controller.Controller;
import com.example.semen.jwtlogin.managers.DataManager;
import com.example.semen.jwtlogin.model.Login;
import com.example.semen.jwtlogin.model.Pet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String token = null;
    public TextInputEditText login;
    public TextInputEditText password;
    public TextInputLayout loginTextInputLayout;
    public TextInputLayout passwordTextInputLayout;
    public AppCompatButton button;
    public List<Pet> pets;

    public String loginString;
    public String passwordString;

    public DataManager dataManager;
    public Api api;
    private SearchView searchView;
    PetAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = Controller.createService(Api.class);
        login = findViewById(R.id.login);
        loginTextInputLayout = findViewById(R.id.loginTextInputLayout);
        password = findViewById(R.id.password);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        button = findViewById(R.id.authenticate);

//        pets = new ArrayList<>();

        dataManager = DataManager.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
//                list();
            }
        });

    }

    void list() {
        api.petList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::onFailure);

    }
    private void handleResult(List<Pet> pets1)
        {
            pets = new ArrayList<>();
            if (pets1 != null ) {
                pets.addAll(pets1);
            }
            System.out.println(pets+" THIS IS PETS WE ARE LOOKING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Intent intent = new Intent(getApplicationContext(), PetActivity.class);
            intent.putExtra("start", (Serializable) pets);
            startActivity(intent);
        }

    private void onFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this, "Failure list", Toast.LENGTH_SHORT).show();
    }




    void login() {
        loginString = login.getText().toString();
        passwordString = password.getText().toString();

        Login login = new Login(loginString, passwordString);
        System.out.println(loginString);

        api.login(login)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult1, this::onFailure1);
        }

        private void handleResult1(Response<ResponseBody> responseBody)
        {
            if (responseBody!=null) {
                token = responseBody.headers().get("access-token");
                dataManager.getPreferencesManager().setAuthToken(token);
                list();

                Toast.makeText(MainActivity.this, dataManager.getPreferencesManager().getAuthToken(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Not successful", Toast.LENGTH_SHORT).show();
            }
        }

        private void onFailure1 (Throwable throwable) {
            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
        }

        }
