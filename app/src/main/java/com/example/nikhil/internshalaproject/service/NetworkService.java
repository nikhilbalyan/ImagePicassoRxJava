package com.example.nikhil.internshalaproject.service;



import com.example.nikhil.internshalaproject.model.WorldPopulationModelResponse;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkService {

    private String baseUrl ="";
    private NetworkAPI networkAPI;
    private OkHttpClient okHttpClient;


    public NetworkService(String url){
        okHttpClient = buildClient();
        baseUrl = url;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        networkAPI = retrofit.create(NetworkAPI.class);

    }

    /**
     * Method to return the API interface.
     * @return
     */
    public NetworkAPI getAPI(){
        return  networkAPI;
    }


    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     * @return
     */
    public OkHttpClient buildClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                // Do anything with response here
                //if we ant to grab a specific cookie or something..
                return response;
            }
        });

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //this is where we will add whatever we want to our request headers.
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            }
        });

        return  builder.build();
    }

    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable){

        Observable<?> preparedObservable = null;
          preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        return preparedObservable;
    }

    /**
     * all the Service alls to use for the retrofit requests.
     */
    public interface NetworkAPI {

        @GET
        Observable<WorldPopulationModelResponse> getAllCitiesObservable(
                @Url String url);
    }

}
