package com.example.bcapro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubjectsActivity extends AppCompatActivity {

    ImageButton back,r,j,d,n,s,ss;

    String message;
    public static final String msg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //code to lock screen in Portrait mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_subject);
        r=findViewById(R.id.rdbms);
        j=findViewById(R.id.java);
        d=findViewById(R.id.dip);
        n=findViewById(R.id.network);
        s=findViewById(R.id.statistics);
        ss=findViewById(R.id.softskill);
        back=findViewById(R.id.back1);

        Intent dintent = getIntent();
        if(dintent.getStringExtra(HomeActivity.dir)!=null) {
            message = dintent.getStringExtra(HomeActivity.dir);
        } else if (dintent.getStringExtra(UnitsActivity.bdir)!=null) {
            message = dintent.getStringExtra(UnitsActivity.bdir);
        } else {
            message = dintent.getStringExtra(PdfActivity.bbdir);
        }


        back.setOnClickListener(view ->{
            Intent intent = new Intent(SubjectsActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        });

        r.setOnClickListener(v -> {
            new animate.anim(r);
            new Handler(Looper.getMainLooper()).postDelayed(() -> selectSubject("RDBMS"), 700);
        });

        j.setOnClickListener(view -> {
            new animate.anim(j);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if((message.equals("notes"))) {
                    Toast.makeText(this, "Coming Soon! Refer Google Classroom for now", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Opening Syllabus!", Toast.LENGTH_SHORT).show();
                    openSyllabusPdf("Java syllabuss.pdf");
                } else {
                    selectSubject("Java");
                }
            }, 700);
        });

        d.setOnClickListener(v -> {
            new animate.anim(d);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if((message.equals("notes"))){
                    selectSubject("DIP");
                } else {
                    Toast.makeText(this, "Newly added subject so no PYQs", Toast.LENGTH_SHORT).show();
                }
            }, 700);
        });

        n.setOnClickListener(v -> {
            new animate.anim(n);
            new Handler(Looper.getMainLooper()).postDelayed(() -> selectSubject("Networking"), 700);
        });

        s.setOnClickListener(view -> {
            new animate.anim(s);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if((message.equals("notes"))) {
                    Toast.makeText(this, "Official notes unavailable!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Opening Syllabus!", Toast.LENGTH_SHORT).show();
                    openSyllabusPdf("Statistics syllabuss.pdf");
                } else {
                    selectSubject("Statistics");
                }
            }, 700);
        });

        ss.setOnClickListener(view -> {
            new animate.anim(ss);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if((message.equals("notes"))){
                    Toast.makeText(this, "Official notes unavailable!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Not worth it \uD83D\uDE1C", Toast.LENGTH_SHORT).show();
                }
            }, 700);
        });
    }

    private void selectSubject(String SUBJECT) {
        Intent intent;
        if ((message.equals("notes"))) {
            intent = new Intent(SubjectsActivity.this, UnitsActivity.class);
        } else {
            intent = new Intent(SubjectsActivity.this, PdfActivity.class);
            SUBJECT+=" PYQ.pdf";
        }
        intent.putExtra(msg,SUBJECT);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    private void openSyllabusPdf(String x) {
        Intent intent = new Intent(SubjectsActivity.this, PdfActivity.class);
        intent.putExtra(msg,x);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}