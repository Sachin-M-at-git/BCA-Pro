package com.example.bcapro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //code to lock screen in Portrait mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        YoYo.with(Techniques.Bounce)
                .duration(2000)
                .repeat(0)
                .playOn(findViewById(R.id.logo));

        YoYo.with(Techniques.FadeIn)
                .duration(1100)
                .repeat(0)
                .playOn(findViewById(R.id.bytext));

        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        }, 3000);
    }
}