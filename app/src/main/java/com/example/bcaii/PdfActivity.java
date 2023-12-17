package com.example.bcaii;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class PdfActivity extends AppCompatActivity {

    TextView t;
    ImageButton back;
    Switch s;

    public static final String msg="",bbdir="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //never sleep
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_pdf);
        PDFView pdfView = findViewById(R.id.pdfView);

        s=findViewById(R.id.pdftheme);
        back=findViewById(R.id.back3);

        Intent intent = getIntent();
        String filename;

        //assigning file name
        if(intent.getStringExtra(UnitsActivity.msgg)!=null){
            filename = intent.getStringExtra(UnitsActivity.msgg);
        } else {
            filename = intent.getStringExtra(SubjectsActivity.msg);
        }
        int l = filename.length();

        back.setOnClickListener(view ->{
            Intent bintent;
            String SUBJECT;
            if(filename.endsWith("syllabuss.pdf") || filename.endsWith("PYQ.pdf"))
            {
                bintent = new Intent(PdfActivity.this, SubjectsActivity.class);
                    //managing open code
                    if(filename.endsWith("PYQ.pdf")){
                        bintent.putExtra(bbdir,"pyq");
                    } else {
                        bintent.putExtra(bbdir,"notes");
                    }

            } else {
                SUBJECT = SubjectNameExtract(filename, l);
                bintent = new Intent(PdfActivity.this, UnitsActivity.class);
                bintent.putExtra(msg,SUBJECT);
                startActivity(bintent);
            }
            startActivity(bintent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        });

        s.setOnClickListener(view -> managePDF(pdfView, filename, s.isChecked()));

        managePDF(pdfView, filename, false);

        //setting Actionbar title
        t=findViewById(R.id.ftab);
        if(filename.endsWith("syllabuss.pdf")){
            t.setText(filename.substring(0,l-5));
        } else {
            t.setText(filename.substring(0,l-4));
        }

        //Promotion
        if (filename.equals("Java PYQ.pdf")) {
            Toast.makeText(this, "From Google Classroom", Toast.LENGTH_SHORT).show();
        } else if (filename.endsWith("PYQ.pdf")){
            Toast.makeText(this, "Provided by L P Naveen", Toast.LENGTH_SHORT).show();
        }
    }

    private void managePDF(PDFView pdfView, String filename, boolean mode) {
        pdfView.fromAsset(filename)
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10)
                .nightMode(mode)
                .load();
    }

    @NonNull
    private static String SubjectNameExtract(String filename, int l) {
        String SUBJECT;
        if(filename.endsWith("syllabuss.pdf")){
            SUBJECT= filename.substring(0, l -14);
        }else if(filename.endsWith("syllabus.pdf")){
            SUBJECT= filename.substring(0, l -13);
        } else {
            SUBJECT= filename.substring(0, l -11);
        }
        return SUBJECT;
    }
}