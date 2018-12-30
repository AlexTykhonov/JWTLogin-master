package com.example.semen.jwtlogin;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.semen.jwtlogin.adapter.PetAdapter;
import com.example.semen.jwtlogin.managers.DataManager;
import com.example.semen.jwtlogin.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetActivity extends AppCompatActivity implements PetAdapter.ContactsAdapterListener {
    public RecyclerView recyclerView;
    public List<Pet> pets;
    public DataManager dataManager;
    public PetAdapter petAdapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
           pets = (List<Pet>) getIntent().getSerializableExtra("start");
            System.out.println(pets.size()+"?????????????????????????");
            petAdapter = new PetAdapter(pets);
       }

        dataManager = DataManager.getInstance();

        recyclerView = findViewById(R.id.pets_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println(pets+ "!!!!!!!!!!!!!!!!!!!!!!!!");
        recyclerView.setAdapter(petAdapter);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = findViewById(R.id.searchView);

        CardView cardView = findViewById(R.id.card_view);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                System.out.println(query+ "  !!!!!!!!!!!!!!!#!#!##!!#!!#!#");
//                        petAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                petAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
    }

    @Override
    public void onContactSelected(Pet pet) {
        Toast.makeText(getApplicationContext(), "Selected: " + pet.getName() + ", " + pet.getId(), Toast.LENGTH_LONG).show();
    }
}