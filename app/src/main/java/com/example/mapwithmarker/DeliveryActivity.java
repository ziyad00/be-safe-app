package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
    }
    public void onClick2(View view){
        Intent intent = new Intent(DeliveryActivity.this, DeliveryItemActivity.class);
        startActivity(intent);

    }
    public void onClick5(View view){
        Intent intent = new Intent(DeliveryActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
