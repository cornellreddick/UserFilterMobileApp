package com.example.userfilterapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userfilterapp.databinding.FragmentSortBinding;
import com.example.userfilterapp.databinding.FragmentStateBinding;
import com.example.userfilterapp.databinding.SortUsersLayoutBinding;


public class SortFragment extends Fragment {

    FragmentSortBinding binding;
    String [] items = {"Age", "Name", "State"};
    public static final int ASC = -1;
    public static final int DESC = 1;
    SortAdapter sortAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
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
        binding = FragmentSortBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(mLayoutManager);
        sortAdapter = new SortAdapter();
        binding.recyclerView.setAdapter(sortAdapter);
    }

    class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> {

        @NonNull
        @Override
        public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //This was create in the code on the layout
            SortUsersLayoutBinding binding = SortUsersLayoutBinding.inflate(getLayoutInflater(), parent, false);
            SortViewHolder viewHolder = new SortViewHolder(binding);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
            String item = items[position];
            holder.setupDate(item);
        }

        @Override
        public int getItemCount() {

            return items.length;
        }

        class SortViewHolder extends RecyclerView.ViewHolder {
            SortUsersLayoutBinding bindingSort;
            String itemSelected;
            public SortViewHolder(SortUsersLayoutBinding binding) {
                    super(binding.getRoot());
                bindingSort = binding;
            }

            public  void setupDate(String item){
                itemSelected = item;
                bindingSort.textViewSortLabel.setText(item);
                bindingSort.imageViewAsc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    sortFragListener.sortBySelected(itemSelected, ASC);

                    }
                });
                bindingSort.imageViewDesc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sortFragListener.sortBySelected(itemSelected, DESC);
                    }
                });

            }

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Sort");
    }

    SortFragListener sortFragListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sortFragListener = (SortFragListener) context;
    }

    interface SortFragListener{
        void sortBySelected(String key, int direction);
    }

}