package com.example.nikhil.internshalaproject;

import android.content.Context;
import android.content.Intent;

public class SharedActivity {


    public static void showFullscreenImage(Context context,String image_url)
    {
        Intent intent=new Intent("android.intent.action.FULLSCREENIMAGEPAGE");
        intent.putExtra("Image URL", image_url);
        context.startActivity(intent);
    }
}
