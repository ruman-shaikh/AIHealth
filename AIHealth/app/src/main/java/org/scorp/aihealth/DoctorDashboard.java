package org.scorp.aihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDashboard extends AppCompatActivity {

    private TextView userID;
    private TextView name;
    private TextView age;
    private TextView phNo;
    private TextView address;
    private TextView specialization;
    private Button patientList;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        userID = findViewById(R.id.user_id);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        phNo = findViewById(R.id.ph_no);
        address = findViewById(R.id.address);
        specialization = findViewById(R.id.specialization);
        patientList = findViewById(R.id.patient_list);

        ip = getIntent().getStringExtra("ip");
        final Doctor doctor = (Doctor) getIntent().getSerializableExtra("Doctor");
        userID.setText(doctor.getUsername());
        name.setText(doctor.getName());
        age.setText(Integer.toString(doctor.getAge()));
        phNo.setText(doctor.getPh_no());
        address.setText(doctor.getAddress());
        specialization.setText(doctor.getSpecialization());

        patientList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PatientList.class);
                intent.putExtra("Username", doctor.getUsername());
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        });

    }
}
