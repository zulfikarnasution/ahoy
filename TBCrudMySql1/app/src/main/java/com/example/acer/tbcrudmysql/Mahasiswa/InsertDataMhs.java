package com.example.acer.tbcrudmysql.Mahasiswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.acer.tbcrudmysql.Mahasiswa.MainActivityMhs;
import com.example.acer.tbcrudmysql.R;
import com.example.acer.tbcrudmysql.Util.AppController;
import com.example.acer.tbcrudmysql.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertDataMhs extends AppCompatActivity {
    EditText npm,nama_lengap,fakultas,prodi;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_mhs);

        /*get data from Intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_npm = data.getStringExtra("npm");
        String intent_nama_lengkap = data.getStringExtra("nama_lengkap");
        String intent_fakultas = data.getStringExtra("fakultas");
        String intent_prodi = data.getStringExtra("prodi");
        /*End get data from Intent*/

        npm = (EditText) findViewById(R.id.inp_npm);
        nama_lengap = (EditText) findViewById(R.id.inp_nama_lengkap);
        fakultas = (EditText) findViewById(R.id.inp_fakultas);
        prodi = (EditText) findViewById(R.id.inp_prodi);
        btnbatal = (Button) findViewById(R.id.btn_cancel);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(InsertDataMhs.this);

        /*kondisi Update / Insert*/
        if (update == 1)
        {
            btnsimpan.setText("Update Data");
            npm.setText(intent_npm);
            npm.setVisibility(View.GONE);
            nama_lengap.setText(intent_nama_lengkap);
            fakultas.setText(intent_fakultas);
            prodi.setText(intent_prodi);

        }




        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });
        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertDataMhs.this,MainActivityMhs.class);
                startActivity(main);
            }
        });
    }

    private  void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDataMhs.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertDataMhs.this,MainActivityMhs.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDataMhs.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("npm",npm.getText().toString());
                map.put("nama_lengkap",nama_lengap.getText().toString());
                map.put("fakultas",fakultas.getText().toString());
                map.put("prodi",prodi.getText().toString());

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(updateReq);
    }
    private void simpanData()
    {
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDataMhs.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertDataMhs.this,MainActivityMhs.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDataMhs.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("npm",npm.getText().toString());
                map.put("nama_lengkap",nama_lengap.getText().toString());
                map.put("fakultas",fakultas.getText().toString());
                map.put("prodi",prodi.getText().toString());

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(sendData);
    }


}
