package com.example.nikhil.internshalaproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
// activity for showing the full screen image
public class FullscreenImageActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_fullscreen_page);

        Bundle bundle=getIntent().getExtras();

        String image=bundle.getString("Image URL");

        ImageView imageView=(ImageView)findViewById(R.id.imageFullscreen);

        Picasso picasso = Picasso.with(this);
        picasso.setIndicatorsEnabled(true);
        picasso.load(image)
            .into(imageView);
    }

}
