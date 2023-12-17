package com.example.bcapro;

import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class animate extends AppCompatActivity {
    public static class anim {
        public anim(ImageButton b) {
            YoYo.with(Techniques.Bounce)
                    .duration(700)
                    .repeat(0)
                    .playOn(b);
        }
    }
}
