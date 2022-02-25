package com.example.userfilterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<DataServices.User> {
    TextView textViewName, textViewState, textViewAge, textViewGroup ;


    public UserAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.User> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_app_list, parent, false);

            textViewName = convertView.findViewById(R.id.textViewName);
            textViewState = convertView.findViewById(R.id.textViewState);
            textViewAge = convertView.findViewById(R.id.textViewAge);
            textViewGroup = convertView.findViewById(R.id.textViewGroup);

        }
        DataServices.User user = getItem(position);

        textViewName.setText(user.name);
        textViewState.setText(user.state);
        textViewAge.setText(String.valueOf(user.age));
        textViewGroup.setText(user.group);

        ImageView image = (ImageView) convertView.findViewById(R.id.genderIcon);

        if(user.gender.equals("Male")){
            image.setImageResource(R.drawable.avatar_male);
        }else{
            image.setImageResource(R.drawable.avatar_female);
        }



        return convertView;
    }
}
