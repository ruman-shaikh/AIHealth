package org.scorp.aihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Prediction extends AppCompatActivity {

    private String ip;
    private String username;
    private Button CKD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        ip = getIntent().getStringExtra("ip");
        username = getIntent().getStringExtra("Username");
        Log.d("devout", username);

        CKD = findViewById(R.id.ckd_btn);

        CKD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predictCKD(ip, username);
            }
        });
    }

    private void predictCKD(String ip, String username) {
        String url = "http://" + ip + ":5000/pat_prediction/ckd/" + username + "/";
        Log.d("devout", url);
        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("devout", "Response Received");
                try {
                    String result = "Server Error";
                    String code = response.getString("code");
                    if (code.equals("200")) {
                        result = response.getString("result");
                    }
                    else{
                        result = response.getString("error");
                    }
                    Toast toast = Toast.makeText(getApplicationContext(),
                            result,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } catch (JSONException e) {
                    Log.e("devout","JSON error");
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "JSON Error",
                            Toast.LENGTH_SHORT);
                    toast.show();
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
