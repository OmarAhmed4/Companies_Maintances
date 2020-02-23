package com.example.companies_maintances;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



public class ActivityForMap extends AppCompatActivity {
    //Constans
    public static  final String FIND_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static  final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static  final int REQUEST_CODE = 1234;
    private boolean isAvaliable = false;
    public GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProvider;
    private final float ZOOM=  15f;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_map);
        getLocationPermission();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocationPermission() {
        String[] permissions = {FIND_LOCATION, COARSE_LOCATION};

                    // ask about if granted before or not
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FIND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                isAvaliable = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        REQUEST_CODE);
            }


        } else {
            // request a permision for user
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        }

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        isAvaliable=false;

        switch(requestCode)
        {
            case REQUEST_CODE:

                if(grantResults.length>0)
                {
                    for(int i=0;i<grantResults.length;i++)
                    {
                        if(grantResults[i]!=getPackageManager().PERMISSION_GRANTED)
                        {
                            isAvaliable=false;
                            return;

                        }
                    }
                isAvaliable=true;
                initMap();
                }
        }
    }



    //TODO: start to work with map
    private void initMap() {
        SupportMapFragment supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
               Toast.makeText(getApplicationContext(),"Ready",Toast.LENGTH_LONG).show();
                mMap=googleMap;

                if(isAvaliable)
                {
                    getDeviceLocation();
                    //mark my place ء
                    mMap.setMyLocationEnabled(true);
                    mMap.getUiSettings().setMyLocationButtonEnabled(false);
                }

            }
        });




    }
    
    public void getDeviceLocation()
    {
        mFusedLocationProvider= LocationServices.getFusedLocationProviderClient(this);
        if(isAvaliable)
        {
            Task location =mFusedLocationProvider.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful())
                    {
                        Location currentLocation= (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),ZOOM);

                    }else
                    {
                        Toast.makeText(ActivityForMap.this,"uable",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        
        
    }

    public void moveCamera(LatLng lating,float zoom)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lating,zoom));


    }


}// End of Class
