package com.example.nikhil.internshalaproject.service;


import android.content.Context;
import android.util.Log;

import com.example.nikhil.internshalaproject.MainActivity;
import com.example.nikhil.internshalaproject.model.WorldPopulationModelResponse;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by nikhil on 12/5/17.
 */

@SuppressWarnings("unchecked")
public class PresentationLayer implements PresenterInteractor{

    private NetworkService service;
    private Subscription subscription;
    private MainActivity view;


    public PresentationLayer(MainActivity view, NetworkService service){
        this.service = service;
        this.view = view;
    }

    public void loadRxData(Context context){
        view.showRxInProcess();

        Observable<WorldPopulationModelResponse> friendResponseObservable = (Observable<WorldPopulationModelResponse>)
                service.getPreparedObservable(service.getAPI().getAllCitiesObservable("tutorial/jsonparsetutorial.txt"));
        subscription = friendResponseObservable.subscribe(new Observer<WorldPopulationModelResponse>() {
            @Override
            public void onCompleted() {
                Log.d("oncompleted", "called");

            }

            @Override
            public void onError(Throwable e) {

                view.showRxFailure(e);
            }

            @Override
            public void onNext(WorldPopulationModelResponse response) {
                view.showRxResults(response);
            }
        });
    }

    public void rxUnSubscribe(){
        if(subscription!=null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}