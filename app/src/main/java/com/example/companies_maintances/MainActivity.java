package com.example.companies_maintances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int ERROR_DIALOG_REQUEST =9001 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button map_Button=(Button)findViewById(R.id.map_Button);
        map_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (MainActivity.this,ActivityForMap.class);
                startActivity(intent);
            }
        });

        isItOk();

    }

    public boolean isItOk() {

        int serviceAvaliability= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(serviceAvaliability== ConnectionResult.SUCCESS)
        {
            return true;
        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(serviceAvaliability))
        {
           /* Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, serviceAvaliability,ERROR_DIALOG_REQUEST)*/
        return true;
        }else
        {
            Toast.makeText(MainActivity.this,"Sorry we Can not resolve",Toast.LENGTH_LONG).show();
        return false;
        }

    }



}// end of class
