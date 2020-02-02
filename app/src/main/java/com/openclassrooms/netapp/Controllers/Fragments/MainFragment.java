package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.netapp.Controllers.Models.GithubUser;
import com.openclassrooms.netapp.Controllers.Utils.GithubCalls;
import com.openclassrooms.netapp.Controllers.Utils.NetworkAsyncTask;
import com.openclassrooms.netapp.R;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements NetworkAsyncTask.Listeners, GithubCalls.Callbacks {

    // FOR DESIGN
    @BindView(R.id.fragment_main_textview) TextView textView;
    private Disposable disposable;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTIONS
    // -----------------



        @OnClick(R.id.fragment_main_button)
        public void submit(View view) {
            this.streamShowString();
        }

    // ------------------------------
    //  Reactive X
    // ------------------------------

    // 1 - Create Observable
    private Observable<String> getObservable(){
        return Observable.just("Cool !");
    }

    // 2 - Create Subscriber
    private DisposableObserver<String> getSubscriber(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String item) {
                textView.setText("Observable emits : "+item);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG","On Complete !!");
            }
        };
    }

    // 3 - Create Stream and execute it
    private void streamShowString(){
        this.disposable = this.getObservable()
                .subscribeWith(getSubscriber());
    }

    // 5 - Dispose subscription
    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // ------------------------------
    //  HTTP REQUEST (Retrofit Way)
    // ------------------------------

    // 4 - Execute HTTP request and update UI
    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartingHTTPRequest();
        GithubCalls.fetchUserFollowing(this, "JakeWharton");
    }

    // 2 - Override callback methods

    @Override
    public void onResponse(@Nullable List<GithubUser> users) {
        // 2.1 - When getting response, we update UI
        if (users != null) this.updateUIWithListOfUsers(users);
    }

    @Override
    public void onFailure() {
        // 2.2 - When getting error, we update UI
        this.updateUIWhenStopingHTTPRequest("An error happened !");
    }

        @Override
        public void onPreExecute() {
            this.updateUIWhenStartingHTTPRequest();
        }

        @Override
        public void doInBackground() { }

        @Override
        public void onPostExecute(String json) {
            this.updateUIWhenStopingHTTPRequest(json);
        }

        // ------------------
        //  UPDATE UI
        // ------------------

        private void updateUIWhenStartingHTTPRequest(){
            this.textView.setText("Downloading...");
        }

        private void updateUIWhenStopingHTTPRequest(String response){
            this.textView.setText(response);
        }

    // 3 - Update UI showing only name of users
    private void updateUIWithListOfUsers(List<GithubUser> users){
        StringBuilder stringBuilder = new StringBuilder();
        for (GithubUser user : users){
            stringBuilder.append("-"+user.getLogin()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }

    }


