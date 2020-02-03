package com.openclassrooms.netapp.Controllers.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.netapp.Controllers.Models.GithubUser;
import com.openclassrooms.netapp.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.fragment_main_item_title) TextView textView;
    // 1 - Adding a TextView and an ImageView
    @BindView(R.id.fragment_main_item_website) TextView texViewWebsite;
    @BindView(R.id.fragment_main_item_image) ImageView imageView;
    // 1 - Declare our ImageButton
    @BindView(R.id.fragment_main_item_delete) ImageButton imageButton;

    // 2 - Declare a Weak Reference to our Callback
    private WeakReference<GithubUserAdapter.Listener> callbackWeakRef;

    public GithubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithGithubUser(GithubUser githubUser, RequestManager glide, GithubUserAdapter.Listener callback){
        this.imageButton.setOnClickListener(this);
        this.callbackWeakRef = new WeakReference<GithubUserAdapter.Listener>(callback);
        this.textView.setText(githubUser.getLogin());
        this.texViewWebsite.setText(githubUser.getHtmlUrl());
        glide.load(githubUser.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    @Override
    public void onClick(View view) {
        // 5 - When a click happens, we fire our listener.
        GithubUserAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickDeleteButton(getAdapterPosition());
    }
}