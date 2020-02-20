package com.example.companies_maintances;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivityForMap extends AppCompatActivity {
    //Constans
    public static String  FIND_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;
    public static String  COARSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
    public static int REQUEST_CODE=1234;
    private boolean isAvaliable=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_map);
    }






    @RequiresApi(api = Build.VERSION_CODES.M)
    private void isMapAvaliable()
    {
        String[] permissions={FIND_LOCATION,COARSE_LOCATION};


        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FIND_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {
                isAvaliable=true;
            }



        }else
        {
            ActivityCompat.requestPermissions(this, permissions,REQUEST_CODE);
        }



    }





}// End of Class
