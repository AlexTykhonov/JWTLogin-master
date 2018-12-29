package com.example.semen.jwtlogin.adapter;

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

    private List<Pet> pets;
    private List<Pet> filteredPets;
    private OnItemClickListener mItemClickListener;
    List<Pet> petAdapter;


    public PetAdapter(List<Pet> pets) {
        this.pets = pets;
        this.filteredPets = pets;
    }

    @NonNull
    @Override
    public PetAdapter.PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.PetViewHolder holder, final int position) {
        final Pet pet = pets.get(position);

        holder.id.setText("id: " + String.valueOf(pet.getId()));
        holder.name.setText("name: " + pet.getName());
//        holder.age.setText("age: " + String.valueOf(pets.get(position).getAge()));
//        holder.userId.setText("userId: " + String.valueOf(pets.get(position).getUserId()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position);
                Intent intent = new Intent(v.getContext(), PetActivity.class);
                intent.putExtra("pet", pet);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s= constraint.toString();
                if (s.isEmpty()) {
                    filteredPets=pets;

                } else {
                    List<Pet> filteredPet = new ArrayList<>();
                    for (Pet p: pets) {
                        System.out.println(p.getId()+"   +GETNAME!!!!");
                 if (p.getId().toString().equals(s.toLowerCase())) {
                 filteredPet.add(p);

                 }
                 }

                 filteredPets=filteredPet;
                    System.out.println(filteredPets.toString()+" PETS STRING ~~~~~~~~~~~~~~~~~");
             }
             FilterResults filteredResults;
                filteredResults = new FilterResults();
                filteredResults.values = filteredPets;
                return filteredResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredPets = (List<Pet>) results.values;
            notifyDataSetChanged();
            }
        };
    }

    public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id;
        TextView name;

        public PetViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
