package com.example.companies_maintances;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickedInterface {

    private static final int ERROR_DIALOG_REQUEST =9001 ;
    Main_Activity_Mvc main_activity_mvc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main_activity_mvc=new Main_Activity_Mvc(LayoutInflater.from(this),null);
        setContentView(main_activity_mvc.getRootView());

        main_activity_mvc.registerListener(this);

        /*Button map_Button=(Button)findViewById(R.id.map_Button);
        map_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(),ActivityForMap.class);
            startActivity(intent);
            }
        });*/

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        main_activity_mvc.unRegisterListener(this);

    }

    @Override
    public void onClickedMethod() {
        Intent intent=new Intent(getApplicationContext(),ActivityForMap.class);
        startActivity(intent);

    }
}// end of class
