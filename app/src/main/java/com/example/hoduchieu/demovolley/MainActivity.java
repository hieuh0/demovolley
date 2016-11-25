package com.example.hoduchieu.demovolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edtTen,edtMail;
    Button btnGoi;
    String URL_POST = "http://192.168.1.104/demovolley/post.php";
    String URL_GET = "http://192.168.1.104/demovolley/getdata.php";
    Custom_Adapter adapter;
    List<SinhVien> list = new ArrayList<>();
    ListView listView;
    RequestQueue requestQueue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTen = (EditText) findViewById(R.id.editText);
        edtMail = (EditText) findViewById(R.id.editText2);
        listView = (ListView) findViewById(R.id.listview);
        btnGoi = (Button) findViewById(R.id.button);

        btnGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertSV();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        adapter = new Custom_Adapter(getApplication(),list);
        listView.setAdapter(adapter);
        getdata();
    }

    private void InsertSV(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                String s = response.trim();
                if(s.equalsIgnoreCase("thanhcong")){
                    Toast.makeText(getApplication(),"Thanh Cong",Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }else {
                    Toast.makeText(getApplication(),"Loi",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error+"", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                String Ten = edtTen.getText().toString();
                String MAIL = edtMail.getText().toString();
                params.put("TEN",Ten);
                params.put("EMAIL",MAIL);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private JsonArrayRequest GetData(String URL){
       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
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
        requestQueue.add(GetData(URL_GET));
    }

    private void ParseData(JSONArray jsonArray){
    for(int i = 0; i < jsonArray.length();i++){
        SinhVien s = new SinhVien();
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(i);
            s.setID(jsonObject.getInt("ID"));
            s.setTen(jsonObject.getString("TEN"));
            s.setTen(jsonObject.getString("EMAIL"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        list.add(s);
    }
adapter.notifyDataSetChanged();

    }

}
