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

public class UserFragment extends Fragment {
    FragmentUserBinding binding;
    ArrayList<DataServices.User> userList = new ArrayList<>();
    UserAdapter userAdapter;



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
        userList.addAll(DataServices.getAllUsers());

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

            }
        });


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
    }

}