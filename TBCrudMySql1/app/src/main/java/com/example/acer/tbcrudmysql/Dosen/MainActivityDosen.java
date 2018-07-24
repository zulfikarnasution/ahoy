package com.example.acer.tbcrudmysql.Dosen;



import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.acer.tbcrudmysql.Adapter.AdapterData;
import com.example.acer.tbcrudmysql.Adapter.AdapterData2;
import com.example.acer.tbcrudmysql.Mahasiswa.DeleteMhs;
import com.example.acer.tbcrudmysql.Mahasiswa.InsertDataMhs;
import com.example.acer.tbcrudmysql.Model.ModelData;
import com.example.acer.tbcrudmysql.R;
import com.example.acer.tbcrudmysql.TampilanUtama;
import com.example.acer.tbcrudmysql.Util.AppController;
import com.example.acer.tbcrudmysql.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityDosen extends AppCompatActivity {
    RecyclerView mReceiclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsert, btnDelete, btnHome;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dosen);

        mReceiclerview = (RecyclerView) findViewById(R.id.receiclerviewTemp);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnHome = (Button) findViewById(R.id.btn_home);
        pd = new ProgressDialog(MainActivityDosen.this);
        mItems = new ArrayList<>();
        loadJson();

        mManager = new LinearLayoutManager(MainActivityDosen.this,LinearLayoutManager.VERTICAL,false);
        mReceiclerview.setLayoutManager(mManager);
        mAdapter = new AdapterData2(MainActivityDosen.this,mItems);
        mReceiclerview.setAdapter(mAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDosen.this,InsertDataDosen.class);
                startActivity(intent);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityDosen.this,TampilanUtama.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(MainActivityDosen.this,DeleteDosen.class);
                startActivity(hapus);
            }
        });

    }


    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pd.cancel();
                Log.d("volley","response : " + response.toString());
                for(int i = 0 ; i < response.length(); i++)
                {
                    try {
                        JSONObject data = response.getJSONObject(i);
                        ModelData md = new ModelData();
                        md.setNik(data.getString("nik"));
                        md.setNama_dosen(data.getString("nama_dosen"));
                        md.setMatakuliah(data.getString("matakuliah"));
                        md.setProdi1(data.getString("prodi1"));
                        mItems.add(md);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(reqData);
    }


}

