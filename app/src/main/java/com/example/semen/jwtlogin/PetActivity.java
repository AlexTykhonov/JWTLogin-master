package com.example.semen.jwtlogin;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.semen.jwtlogin.adapter.PetAdapter;
import com.example.semen.jwtlogin.managers.DataManager;
import com.example.semen.jwtlogin.model.DaoSession;
import com.example.semen.jwtlogin.model.Pet;

import java.util.List;

public class PetActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public List<Pet> pets = null;
    public DataManager dataManager;
    public DaoSession daoSession;
    public PetAdapter petAdapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

//        pets = (List<Pet>) getIntent().getSerializableExtra("start");

        dataManager = DataManager.getInstance();
        daoSession = dataManager.getDaoSession();
        pets = daoSession.getPetDao().loadAll();

        recyclerView = findViewById(R.id.pets_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PetAdapter(pets));

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        petAdapter = new PetAdapter(this.pets);
        searchView = findViewById(R.id.searchView);
        // // // /
        CardView cardView = findViewById(R.id.card_view);
// // // /
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(query+ "  !!!!!!!!!!!!!!!#!#!##!!#!!#!#");
                        petAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                petAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

}