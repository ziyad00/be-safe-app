package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DeliveryItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_item);
    }
    public void onClick5(View view){
        Intent intent = new Intent(DeliveryItemActivity.this, DeliveryActivity.class);
        startActivity(intent);

    }
}
