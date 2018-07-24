package com.example.acer.tbcrudmysql.Jadwal;


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

public class InsertDataJadwal extends AppCompatActivity {
    EditText id_matkul,nama_matkul,hari,ruangan;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_jadwal);

        /*get data from Intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_id_matkul = data.getStringExtra("id_matkul");
        String intent_nama_matkul = data.getStringExtra("nama_matkul");
        String intent_hari = data.getStringExtra("hari");
        String intent_ruangan = data.getStringExtra("ruangan");
        /*End get data from Intent*/

        id_matkul = (EditText) findViewById(R.id.inp_id);
        nama_matkul = (EditText) findViewById(R.id.inp_nama_matkul);
        hari = (EditText) findViewById(R.id.inp_hari);
        ruangan = (EditText) findViewById(R.id.inp_ruangan);
        btnbatal = (Button) findViewById(R.id.btn_cancel);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(InsertDataJadwal.this);

        /*kondisi Update / Insert*/
        if (update == 1)
        {
            btnsimpan.setText("Update Data");
            id_matkul.setText(intent_id_matkul);
            id_matkul.setVisibility(View.GONE);
            nama_matkul.setText(intent_nama_matkul);
            hari.setText(intent_hari);
            ruangan.setText(intent_ruangan);

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
                Intent main = new Intent(InsertDataJadwal.this,MainActivityJadwal.class);
                startActivity(main);
            }
        });
    }

    private  void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDataJadwal.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertDataJadwal.this,MainActivityJadwal.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDataJadwal.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_matkul",id_matkul.getText().toString());
                map.put("nama_matkul",nama_matkul.getText().toString());
                map.put("hari",hari.getText().toString());
                map.put("ruangan",ruangan.getText().toString());

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

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDataJadwal.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertDataJadwal.this,MainActivityJadwal.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDataJadwal.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_matkul",id_matkul.getText().toString());
                map.put("nama_matkul",nama_matkul.getText().toString());
                map.put("hari",hari.getText().toString());
                map.put("ruangan",ruangan.getText().toString());

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(sendData);
    }


}

