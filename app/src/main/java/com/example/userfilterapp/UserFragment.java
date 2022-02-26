package com.example.userfilterapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userfilterapp.databinding.FragmentUserBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserFragment extends Fragment {
    FragmentUserBinding binding;
    ArrayList<DataServices.User> userList = new ArrayList<>();
    UserAdapter userAdapter;
    String filteredState;
    String sortKey;
    int sortDirection;

    //The method is used to retrieve the state value from the interface in the MainActivity
    void setFilterState(String state){
        filteredState = state;
    }
    void setSortScheme(String key, int direction){
        sortKey = key;
        sortDirection = direction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //clones
        userList.clear();

        //Filtering the states
        if (filteredState == null || filteredState.equals("All States")){
            userList.addAll(DataServices.getAllUsers());
        }else{
            //Only pick users with the state selected.
            ArrayList<DataServices.User> allUsers = DataServices.getAllUsers();
            for (DataServices.User user: allUsers) {
                if (user.state.equals(filteredState)){
                    userList.add(user);
                }
            }
        }
        //Sort
        if (sortKey != null){
            if (sortKey.equals("Age")){
                Collections.sort(userList, new Comparator<DataServices.User>() {
                    @Override
                    public int compare(DataServices.User user1, DataServices.User user2) {
                       return sortDirection * (user1.age - user2.age);
                    }
                });
            }else if (sortKey.equals("Name")){
                Collections.sort(userList, new Comparator<DataServices.User>() {
                    @Override
                    public int compare(DataServices.User user1, DataServices.User user2) {
                        return sortDirection * user1.name.compareTo(user2.name);
                    }
                });
            }else if (sortKey.equals("State")){
                Collections.sort(userList, new Comparator<DataServices.User>() {
                    @Override
                    public int compare(DataServices.User user1, DataServices.User user2) {
                        return sortDirection * user1.state.compareTo(user2.state);
                    }
                });
            }
        }
        

        userAdapter = new UserAdapter(getContext(), R.layout.user_app_list, userList);
        binding.userListView.setAdapter(userAdapter);


        binding.filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFragmentListener.gotoFilterByState();
            }
        });

        binding.sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            userFragmentListener.goToSort();

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Users");
    }

    UserFragmentListener userFragmentListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        userFragmentListener = (UserFragmentListener) context;
    }

    //This is used to pull data from UserFragment and Send to Activity
    interface UserFragmentListener{
        void gotoFilterByState();
        void goToSort();
    }

}