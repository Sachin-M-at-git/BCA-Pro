package com.example.bcaii;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class HomeActivity extends AppCompatActivity {

    public static final String dir ="";
    private static final String PERMISSION_POST_NOTIFICATIONS = "Manifest.permission.POST_NOTIFICATIONS";

    private static final int NOTIFICATION_PERMISSION_CODE = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //notification permission request part
        requestRuntimePermission();
    }

    private void requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(this,PERMISSION_POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSION_POST_NOTIFICATIONS)) {
            new AlertDialog.Builder(this)
                    .setTitle("Please allow notification permission! \uD83D\uDD14")
                    .setMessage("Inorder to get instant updates about new releases of the app, please enable notification permission")
                    .setPositiveButton("OK",(dialog, which) -> {
                        ActivityCompat.requestPermissions(HomeActivity.this,new String[] {android.Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
                        dialog.dismiss();})
                    .setNegativeButton("CANCEL",(dialog, which) -> dialog.dismiss())
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[] {Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == NOTIFICATION_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            }else if(!ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSION_POST_NOTIFICATIONS)){
                new AlertDialog.Builder(this)
                        .setTitle("Please allow notification permission! \uD83D\uDD14")
                        .setMessage("Inorder to get instant updates about new releases of the app, please enable notification permission")
                        .setCancelable(false)
                        .setNegativeButton("CANCEL",(dialog, which) -> dialog.dismiss())
                        .setPositiveButton("GO TO SETTINGS", (dialog, which) -> {
                            Intent intent = new Intent((Settings.ACTION_APPLICATION_DETAILS_SETTINGS));
                            Uri uri = Uri.fromParts("package",getPackageName(),null);
                            intent.setData(uri);
                            startActivity(intent);

                            dialog.dismiss();
                        })
                        .create().show();
            }else {
                requestRuntimePermission();
            }
        }
    }

    public void opennotes(View view){

        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.notes));

        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            Intent intent = new Intent(HomeActivity.this, SubjectsActivity.class);
            intent.putExtra(dir,"notes");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }, 700);
    }

    public void openPYQ(View view){

        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.pyq));

        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            Intent intent = new Intent(HomeActivity.this, SubjectsActivity.class);
            intent.putExtra(dir,"pyq");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }, 700);
    }
}