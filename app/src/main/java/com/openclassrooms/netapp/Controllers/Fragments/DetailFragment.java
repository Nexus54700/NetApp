package com.openclassrooms.netapp.Controllers.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.netapp.Controllers.Views.GithubUserAdapter;
import com.openclassrooms.netapp.R;

import butterknife.ButterKnife;


public class DetailFragment extends Fragment implements GithubUserAdapter.Listener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onClickDeleteButton(int position) {

    }
}
