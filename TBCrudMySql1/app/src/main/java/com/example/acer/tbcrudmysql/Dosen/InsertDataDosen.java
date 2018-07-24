package com.example.acer.tbcrudmysql.Dosen;

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

public class InsertDataDosen extends AppCompatActivity {
    EditText nik,nama_dosen,matakuliah,prodi1;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_dosen);

        /*get data from Intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_nik = data.getStringExtra("nik");
        String intent_nama_dosen = data.getStringExtra("nama_dosen");
        String intent_matakuliah = data.getStringExtra("matakuliah");
        String intent_prodi1 = data.getStringExtra("prodi1");
        /*End get data from Intent*/

        nik = (EditText) findViewById(R.id.inp_nik);
        nama_dosen = (EditText) findViewById(R.id.inp_nama_dosen);
        matakuliah = (EditText) findViewById(R.id.inp_matakuliah);
        prodi1 = (EditText) findViewById(R.id.inp_prodi1);
        btnbatal = (Button) findViewById(R.id.btn_cancel);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(InsertDataDosen.this);

        /*kondisi Update / Insert*/
        if (update == 1)
        {
            btnsimpan.setText("Update Data");
            nik.setText(intent_nik);
            nik.setVisibility(View.GONE);
            nama_dosen.setText(intent_nama_dosen);
            matakuliah.setText(intent_matakuliah);
            prodi1.setText(intent_prodi1);

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
                Intent main = new Intent(InsertDataDosen.this,MainActivityDosen.class);
                startActivity(main);
            }
        });
    }

    private  void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDataDosen.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertDataDosen.this,MainActivityDosen.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDataDosen.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nik",nik.getText().toString());
                map.put("nama_dosen",nama_dosen.getText().toString());
                map.put("matakuliah",matakuliah.getText().toString());
                map.put("prodi1",prodi1.getText().toString());

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

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDataDosen.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertDataDosen.this,MainActivityDosen.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDataDosen.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nik",nik.getText().toString());
                map.put("nama_dosen",nama_dosen.getText().toString());
                map.put("matakuliah",matakuliah.getText().toString());
                map.put("prodi1",prodi1.getText().toString());

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(sendData);
    }


}
