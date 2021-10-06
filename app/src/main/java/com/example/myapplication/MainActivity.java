package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private static final int PHOTO_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
            Bundle b = new Bundle();
            b.putBoolean("granted", true);
            intent.putExtras(b);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                    PHOTO_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);
        if (requestCode == PHOTO_REQUEST_CODE) {
            if (grantResults.length > 0 & grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("granted", true);
                intent.putExtras(b);
                startActivity(intent);
            }

            }
            else {
                return;
            }


    }

}