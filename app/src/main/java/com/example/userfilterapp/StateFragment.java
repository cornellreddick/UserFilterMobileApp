package com.example.userfilterapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.userfilterapp.databinding.FragmentStateBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class StateFragment extends Fragment {

    FragmentStateBinding binding;
    ArrayList<String> states = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

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
        binding = FragmentStateBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get list of states

        ArrayList<DataServices.User> usersList = DataServices.getAllUsers();
        HashSet<String> statesSet = new HashSet<>();

        //iterate through users and retrieve states
        for (DataServices.User user : usersList) {
            statesSet.add(user.state);
        }

        //add states to the list.
        states.addAll(statesSet);

        //Sort states
        Collections.sort(states);

        //Adding String to the beginning of the list
        states.add(0, "All States");

        //Create list in State Fragment
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, states);
        binding.stateListView.setAdapter(arrayAdapter);
        binding.stateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

               stateListener.selectFilteredState(arrayAdapter.getItem(position));

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Filter By State");
    }

    StateListener stateListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        stateListener = (StateListener) context;
    }

    interface StateListener{
        void selectFilteredState(String state);
    }

}