package com.example.nikhil.internshalaproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nikhil.internshalaproject.model.WorldPopulation;
import com.example.nikhil.internshalaproject.model.WorldPopulationModelResponse;
import com.example.nikhil.internshalaproject.service.NetworkService;
import com.example.nikhil.internshalaproject.service.PresentationLayer;
import com.example.nikhil.internshalaproject.service.PresenterInteractor;
import com.example.nikhil.internshalaproject.service.RxApplication;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> images;
    private Context context;

    private NetworkService service;
    private boolean rxCallInWorks = false;
    private PresenterInteractor presenter;
    private static final String EXTRA_RX = "EXTRA_RX";
    private ProgressDialog loading;
    private ImageGridAdapter imageGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        imageGridAdapter = new ImageGridAdapter(this);
        images = new ArrayList<String>();
        service = ((RxApplication)getApplication()).getNetworkService();
        presenter = new PresentationLayer(this, service);
        final boolean flag = NetworkTest.CheckConnection(context);
        if (flag) {
            presenter.loadRxData(this);
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.rxUnSubscribe();

    }

    public void showRxResults(WorldPopulationModelResponse worldPopulationModelResponse) {
        loading.dismiss();
        List<WorldPopulation> worldPopulations = new ArrayList<>();
        worldPopulations = worldPopulationModelResponse.getWorldpopulation();
        inflateImageGrid(worldPopulations);
    }

    public void showRxFailure(Throwable t) {
        loading.dismiss();
    }

    public void showRxInProcess() {
        loading = ProgressDialog.show(this, "Getting things done", "Please wait...",
                false, false);
    }

    public void inflateImageGrid(List<WorldPopulation> worldPopulations) {

        for(int i = 0; i < worldPopulations.size(); i++) {
            images.add(i,worldPopulations.get(i).getFlag());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listOfItems);

        GridLayoutManager glm = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(glm);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imageGridAdapter);
        // pass the list of images url to the adapter
        imageGridAdapter.addAll(images);

    }

}
