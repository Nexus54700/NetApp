package com.openclassrooms.netapp.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.openclassrooms.netapp.R;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Add back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detail");

    }

}
