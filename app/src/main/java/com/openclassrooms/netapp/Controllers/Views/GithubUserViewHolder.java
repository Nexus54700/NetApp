package com.openclassrooms.netapp.Controllers.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.netapp.Controllers.Models.GithubUser;
import com.openclassrooms.netapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title)
    TextView textView;

    public GithubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithGithubUser(GithubUser githubUser){
        this.textView.setText(githubUser.getLogin());
    }
}