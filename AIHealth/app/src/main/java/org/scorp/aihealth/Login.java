package org.scorp.aihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText editURL;
    private EditText editEmail;
    private EditText editPwd;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editURL = (EditText) findViewById(R.id.editURL);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPwd = (EditText) findViewById(R.id.editPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String pwd = editPwd.getText().toString();
                String ip = editURL.getText().toString();
                String LoginType = getIntent().getStringExtra("LoginType");
                Log.d("devout", ip);
                if (email.isEmpty() || pwd.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Email or Password Field Empty",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    Log.e("devout", "Email or Password Field Empty");
                }
                else
                    verifyLogin(ip, LoginType, email, pwd);
            }
        });
    }

    public void verifyLogin(String ip, final String LoginType, final String usrname, String passwd) {
        String url = "http://" + ip + ":5000/" + LoginType + "/" + usrname + "/" + passwd + "/";
        Log.d("devout", url);

        final String ip_address = ip;

        RequestQueue queue = Volley.newRequestQueue(this);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("devout", "Response Received");
                try {
                    String results = response.getString("code");
                    Log.d("devout", "Request code is " + results);
                    if (results.equals("200")) {
                        Log.d("devout", "Login Verified");
                        String name = response.getString("fname") + " " + response.getString("lanme");
                        Log.d("devout", name);
                        if (LoginType.equals("doc_log")) {
                            Doctor doctor = new Doctor(
                                    usrname,
                                    name,
                                    Integer.parseInt(response.getString("age")),
                                    response.getString("specialization"),
                                    response.getString("address"),
                                    response.getString("ph_no")
                            );
                            Intent intent =  new Intent(getApplicationContext(), DoctorDashboard.class);
                            intent.putExtra("Doctor", doctor);
                            intent.putExtra("ip", ip_address);
                            startActivity(intent);
                            Log.d("devout", "Doctor");
                        }
                        else {
                            Patient patient = new Patient(
                                    usrname,
                                    name,
                                    Integer.parseInt(response.getString("age")),
                                    response.getString("ph_no")
                            );
                            Intent intent =  new Intent(getApplicationContext(), PatientDashboard.class);
                            intent.putExtra("Patient", patient);
                            intent.putExtra("ip", ip_address);
                            startActivity(intent);
                            Log.d("devout", "Patient");
                        }
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Incorrect username/password",
                                Toast.LENGTH_SHORT);

                        toast.show();
                    }
                } catch (JSONException e) {
                    Log.e("devout","JSON error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Network Error",
                        Toast.LENGTH_SHORT);

                toast.show();
                Log.e("devout", "Listener error " + error.getMessage());
            }
        });

        queue.add(request);
    }

}
