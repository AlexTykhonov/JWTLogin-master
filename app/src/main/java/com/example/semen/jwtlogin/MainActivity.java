package com.example.semen.jwtlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.semen.jwtlogin.managers.DataManager;
import com.example.semen.jwtlogin.model.Login;
import com.example.semen.jwtlogin.model.Pet;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

//        list.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list();
//            }
//        });

    }

    void list() {
        Call<List<Pet>> call = dataManager.getApi().petList();

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                pets = new ArrayList<>();
               if (response.body()!= null ) {
                   pets.addAll(response.body());
               }

                dataManager.getDaoSession().getPetDao().insertOrReplaceInTx(pets);

                Intent intent = new Intent(getApplicationContext(), PetActivity.class);
//                intent.putExtra("start", (Serializable) pets);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure list", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void login() {
        loginString = login.getText().toString();
        passwordString = password.getText().toString();

        Login login = new Login(loginString, passwordString);
        System.out.println(loginString);

        Call<ResponseBody> call = dataManager.getApi().login(login);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    token = response.headers().get("access-token");
                    dataManager.getPreferencesManager().setAuthToken(token);
                    list();
                    Toast.makeText(MainActivity.this, dataManager.getPreferencesManager().getAuthToken(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
