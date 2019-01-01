package com.example.semen.jwtlogin;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.semen.jwtlogin.model.Pet;

import java.util.List;

public class PetCalback extends DiffUtil.Callback {

        private final List<Pet> mOldPetList;
        private final List<Pet> mNewPetList;

        public PetCalback(List<Pet> oldEmployeeList, List<Pet> newEmployeeList) {
            this.mOldPetList = oldEmployeeList;
            this.mNewPetList = newEmployeeList;
        }

        @Override
        public int getOldListSize() {
            return mOldPetList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewPetList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldPetList.get(oldItemPosition).getId() == mNewPetList.get(
                    newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Pet oldEmployee = mOldPetList.get(oldItemPosition);
            final Pet newEmployee = mNewPetList.get(newItemPosition);

            return oldEmployee.getName().equals(newEmployee.getName());
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            // Implement method if you're going to use ItemAnimator
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }


