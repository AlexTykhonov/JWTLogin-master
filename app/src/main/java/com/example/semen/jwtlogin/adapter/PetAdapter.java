package com.example.semen.jwtlogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.example.semen.jwtlogin.PetActivity;
import com.example.semen.jwtlogin.R;
import com.example.semen.jwtlogin.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> implements Filterable{

    private Context context;
    private List<Pet> pets;
    private List<Pet> petListFiltered;
    List<Pet> petAdapter;
    private ContactsAdapterListener listener;


    public PetAdapter(List<Pet> pets) {
        this.pets = pets;
        this.petListFiltered = pets;
    }

    @NonNull
    @Override
    public PetAdapter.PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.PetViewHolder holder, final int position) {
        final Pet pet = petListFiltered.get(position);

        holder.id.setText("id: " + String.valueOf(pet.getId()));
        holder.name.setText("name: " + pet.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position);
//                Intent intent = new Intent(v.getContext(), PetActivity.class);
//                intent.putExtra("pet", pet);
//                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return petListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    petListFiltered = pets;
                } else {
                    List<Pet> filteredList = new ArrayList<>();
                    System.out.println(pets.size()+"THIS IS PETS SIZE IN THE FILTERING");
                    for (Pet row : pets) {
                        System.out.println(row.getId()+"THIS IS ROW ID IN THE FILTERING!!!!!!!!!");
                        System.out.println(charString);
                        if (row.getId().toString().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    System.out.println(filteredList.size()+ " А ЭТО НАШ ФИЛЬТРОВАНЫЙ ЛИСТ ПОСЛЕ ФИЛЬТРА"+filteredList);
                    petListFiltered = filteredList;
                    System.out.println(petListFiltered+" <--!!!THIS OBJECT CONTAINS THE RESULT OF FILTERING !");
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = petListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                petListFiltered = (List<Pet>) filterResults.values;
                System.out.println(petListFiltered+" <--!!!THIS IS >>>>>>>> !!!!!! >>>>>PUBLISHED RESULT OF FILTERING !");
                notifyDataSetChanged();
            }
        };
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        private ContactsAdapterListener listener;

        public PetViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
        }
    }

    public void setData(List<Pet> list){
        this.pets = list;
        notifyDataSetChanged();
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Pet pet);
    }
}
