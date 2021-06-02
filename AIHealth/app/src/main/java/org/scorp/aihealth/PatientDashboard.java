package org.scorp.aihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientDashboard extends AppCompatActivity {

    private TextView userID;
    private TextView name;
    private TextView age;
    private TextView phNo;
    private Button history;
    private Button prediction;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        userID = findViewById(R.id.user_id);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        phNo = findViewById(R.id.ph_no);
        history = findViewById(R.id.doctor_history);
        prediction = findViewById(R.id.prediction);

        final Patient patient = (Patient) getIntent().getSerializableExtra("Patient");
        ip = getIntent().getStringExtra("ip");
        userID.setText(patient.getUsername());
        name.setText(patient.getName());
        age.setText(Integer.toString(patient.getAge()));
        phNo.setText(patient.getPh_no());

        prediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Prediction.class);
                intent.putExtra("Username", patient.getUsername());
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        });

    }
}
