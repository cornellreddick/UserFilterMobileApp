package com.example.userfilterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.userfilterapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements UserFragment.UserFragmentListener, StateFragment.StateListener, SortFragment.SortFragListener {
    ActivityMainBinding binding;
    final String TAG = "Demo";
    private static final String USER_FRAG = "user-fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        //USER_FRAG is a tag that is used, so I can retrieve what is on the backstack.
       getSupportFragmentManager().beginTransaction()
               .add(R.id.containerView, new UserFragment(), "user-fragment")
               .commit();

    }

    @Override
    public void gotoFilterByState() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new StateFragment())
                .addToBackStack(null)
                .commit();

    }
    @Override
    public void goToSort(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new SortFragment())
                .addToBackStack(null)
                .commit();
    }

    //To pass select state value from the backstack you need to pass a tag
    @Override
    public void selectFilteredState(String state) {
        //Most be casted to the UserFragment to work.
        UserFragment userFragment = (UserFragment) getSupportFragmentManager().findFragmentByTag("user-fragment");
        //Always check for null
        if (userFragment !=null){
            userFragment.setFilterState(state);
        }
        getSupportFragmentManager().popBackStack();
    }


    @Override
    public void sortBySelected(String key, int direction) {
        //Most be casted to the UserFragment to work.
        UserFragment userFragment = (UserFragment) getSupportFragmentManager().findFragmentByTag("user-fragment");
        //Always check for null
        if (userFragment !=null){
            userFragment.setSortScheme(key, direction);
        }
        getSupportFragmentManager().popBackStack();
    }
}