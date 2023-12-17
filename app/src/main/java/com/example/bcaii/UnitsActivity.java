package com.example.bcaii;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UnitsActivity extends AppCompatActivity {

    ImageButton back,u1,u2,u3,u4,u5,s;

    public static final String msgg="",bdir="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //code to lock screen in Portrait mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_units);
        u1=findViewById(R.id.unit1);
        u2=findViewById(R.id.unit2);
        u3=findViewById(R.id.unit3);
        u4=findViewById(R.id.unit4);
        u5=findViewById(R.id.unit5);
        s=findViewById(R.id.syllabus);
        back=findViewById(R.id.back2);

        back.setOnClickListener(view ->{
            Intent intent = new Intent(UnitsActivity.this, SubjectsActivity.class);
            intent.putExtra(bdir,"notes");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        });



        u1.setOnClickListener(view -> {
            new animate.anim(u1);
            new Handler(Looper.getMainLooper()).postDelayed(() -> openUnitPdf(" Unit 1.pdf"), 700);});

        u2.setOnClickListener(view -> {
            new animate.anim(u2);
            new Handler(Looper.getMainLooper()).postDelayed(() -> openUnitPdf(" Unit 2.pdf"), 700);});

        u3.setOnClickListener(view -> {
            new animate.anim(u3);
            new Handler(Looper.getMainLooper()).postDelayed(() -> openUnitPdf(" Unit 3.pdf"), 700);});

        u4.setOnClickListener(view -> {
            new animate.anim(u4);
            new Handler(Looper.getMainLooper()).postDelayed(() -> openUnitPdf(" Unit 4.pdf"), 700);});

        u5.setOnClickListener(view -> {
            new animate.anim(u5);
            new Handler(Looper.getMainLooper()).postDelayed(() -> openUnitPdf(" Unit 5.pdf"), 700);});

        s.setOnClickListener(view -> {
            new animate.anim(s);
            new Handler(Looper.getMainLooper()).postDelayed(() -> openUnitPdf(" syllabus.pdf"), 700);});

    }

    private void openUnitPdf(String x) {
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(SubjectsActivity.msg);
        message = message+ x;
        Intent intent2 = new Intent(UnitsActivity.this, PdfActivity.class);
        intent2.putExtra(msgg,message);
        startActivity(intent2);
    }
}