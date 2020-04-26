package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    public void onClick(View view){
        Intent intent = new Intent(HomeActivity.this, StatActivity.class);
        startActivity(intent);

    }
    public void onClick2(View view){
        Intent intent = new Intent(HomeActivity.this, MapsMarkerActivity.class);
        startActivity(intent);

    }
    public void onClick3(View view){
        Intent intent = new Intent(HomeActivity.this, DeliveryActivity.class);
        startActivity(intent);

    }
    public void onClick4(View view){
        Intent intent = new Intent(HomeActivity.this, CallADoctorActivity.class);
        startActivity(intent);

    }
    public void onClick5(View view){
        Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

}
