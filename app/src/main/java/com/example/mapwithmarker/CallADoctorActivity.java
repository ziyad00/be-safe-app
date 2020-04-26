package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CallADoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_a_doctor);
    }
    public void onClick5(View view){
        Intent intent = new Intent(CallADoctorActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
