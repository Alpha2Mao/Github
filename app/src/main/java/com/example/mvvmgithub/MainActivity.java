package com.example.mvvmgithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.example.mvvmgithub.databinding.ActivityMainBinding;
import com.example.mvvmgithub.model.User;
import com.example.mvvmgithub.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        final UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.i(TAG, "The user is changed");
                if (user != null) {
                    activityMainBinding.setUser(user);
                    Log.d(TAG, "The user is " + user.toString());
                } else{
                    Log.d(TAG, "The user is null");
                }
            }
        });

        final SwipeRefreshLayout swipeRefreshLayout = activityMainBinding.swipeRefresh;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userViewModel.refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}