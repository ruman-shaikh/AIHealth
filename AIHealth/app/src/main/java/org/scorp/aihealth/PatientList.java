package org.scorp.aihealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PatientList extends AppCompatActivity implements PatientAdapter.OnItemClickListener {

    private String ip;
    private String username;
    private RecyclerView recyclerView;
    private PatientAdapter patientAdapter;
    private ArrayList<Patient> patientArrayList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        ip = getIntent().getStringExtra("ip");
        username = getIntent().getStringExtra("Username");
        //Log.d("devout", "list");
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Log.d("devout", "View");

        patientArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJSON(username, ip);
    }

    private void parseJSON(String username, String ip) {
        String url = "http://" + ip + ":5000/doc/patlist/" + username + "/";
        Log.d("devout", "URL is " + url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String code = response.getString("code");
                    String message = response.getString("message");
                    Toast toast = Toast.makeText(getApplicationContext(),
                            message,
                            Toast.LENGTH_SHORT);

                    toast.show();
                    if (code.equals("200")) {
                        Log.d("devout", "Waiting for response");
                        JSONArray patList = response.getJSONArray("patientList");
                        for (int i = 0; i < patList.length(); i++) {
                            JSONObject pat = patList.getJSONObject(i);
                            String name = pat.getString("fname") + " " + pat.getString("lname");
                            Log.d("devout", name);

                            patientArrayList.add(new Patient(
                                    pat.getString("username"),
                                    name,
                                    Integer.parseInt(pat.getString("age")),
                                    pat.getString("ph_no")
                            ));
                        }

                        patientAdapter = new PatientAdapter(PatientList.this, patientArrayList);
                        recyclerView.setAdapter(patientAdapter);
                        patientAdapter.setOnItemClickListener(PatientList.this);

                        Log.d("devout", "Received and parsed");
                    }
                } catch (JSONException e) {
                    Log.d("devout", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("devout", "Listener error " + error.getMessage());
            }
        });

        requestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {
        Log.d("devout", "Item Clicked");
        Intent intent = new Intent(getApplicationContext(), PatientDashboard.class);
        Patient currentPatient = patientArrayList.get(position);
        intent.putExtra("ip", ip);
        intent.putExtra("Patient", currentPatient);
        startActivity(intent);
    }
}
