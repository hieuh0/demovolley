package com.example.hoduchieu.demovolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Info_Activity extends AppCompatActivity {
    EditText edt1,edt2;
    String URL = "http://192.168.1.109/demovolley/getdatabyID.php?ID=";
    RequestQueue requestQueue;
    String ten,email;
    int ID;
    Button btnxoa,btnsua;
    String URL_DELERE = "http://192.168.1.109/demovolley/delete.php";
    String URL_UPDATE = "http://192.168.1.109/demovolley/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_);
        requestQueue = Volley.newRequestQueue(getApplication());
        edt1 = (EditText) findViewById(R.id.editText3);
        edt2 = (EditText) findViewById(R.id.editText4);
        Intent intent = getIntent();
        ID = intent.getIntExtra("ID",0);
        edt1.setText(ID+"");
        getdata();
        btnxoa = (Button) findViewById(R.id.btnXoa);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELERE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Info_Activity.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params = new HashMap<String, String>();
                        params.put("ID",ID+"");
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        btnsua = (Button)findViewById(R.id.btnSua);
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Info_Activity.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params = new HashMap<String, String>();
                        String newEmail = edt2.getText().toString();
                        params.put("ID",ID+"");
                        params.put("EMAIL",newEmail);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
    private JsonArrayRequest GetData(int ID){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL+String.valueOf(ID), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ParseData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return jsonArrayRequest;
    }

    private void getdata(){
        requestQueue.add(GetData(ID));
    }

    private void ParseData(JSONArray jsonArray){
        for(int i = 0; i < jsonArray.length();i++){
            SinhVien s = new SinhVien();
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
             ten =jsonObject.getString("TEN");
             email=jsonObject.getString("EMAIL");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            edt2.setText(email);
        }

    }
}
