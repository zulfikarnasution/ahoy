package com.example.acer.tbcrudmysql.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.tbcrudmysql.Dosen.InsertDataDosen;
import com.example.acer.tbcrudmysql.Mahasiswa.InsertDataMhs;
import com.example.acer.tbcrudmysql.Model.ModelData;
import com.example.acer.tbcrudmysql.R;

import java.util.List;

public class AdapterData2 extends RecyclerView.Adapter<AdapterData2.HolderData>{
    private List<ModelData>mItems ;
    private Context context;


    public AdapterData2 (Context context, List<ModelData>items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_dosen,parent,false);

        HolderData holderData = new HolderData(layout);

        return holderData;

    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnama_dosen.setText(md.getNama_dosen());
        holder.tvnik.setText(md.getNik());
        holder.tvmatakuliah.setText(md.getMatakuliah());
        holder.tvprodi1.setText(md.getProdi1());
        holder.md=md;

    }




    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {

        TextView tvnama_dosen,tvnik,tvmatakuliah,tvprodi1;

        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvnama_dosen = (TextView) view.findViewById(R.id.nama_dosen);
            tvnik = (TextView) view.findViewById(R.id.nik);
            tvmatakuliah = (TextView) view.findViewById(R.id.matakuliah);
            tvprodi1 = (TextView) view.findViewById(R.id.prodi1);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent (context, InsertDataDosen.class);
                    update.putExtra("update",1);
                    update.putExtra("nik",md.getNik());
                    update.putExtra("nama_dosen",md.getNama_dosen());
                    update.putExtra("matakuliah",md.getMatakuliah());
                    update.putExtra("prodi1",md.getProdi1());

                    context.startActivity(update);
                }
            });

        }
    }
}

