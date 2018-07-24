package com.example.acer.tbcrudmysql.Adapter;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.tbcrudmysql.Jadwal.InsertDataJadwal;
import com.example.acer.tbcrudmysql.Mahasiswa.InsertDataMhs;
import com.example.acer.tbcrudmysql.Model.ModelData;
import com.example.acer.tbcrudmysql.R;

import java.util.List;

public class AdapterData1 extends RecyclerView.Adapter<AdapterData1.HolderData>{
    private List<ModelData>mItems ;
    private Context context;


    public AdapterData1 (Context context, List<ModelData>items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_jadwal,parent,false);

        HolderData holderData = new HolderData(layout);

        return holderData;

    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnama_matkul.setText(md.getNama_matkul());
        holder.tvid_matkul.setText(md.getId_matkul());
        holder.tvhari.setText(md.getHari());
        holder.tvruangan.setText(md.getRuangan());
        holder.md=md;

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {

        TextView tvnama_matkul,tvid_matkul,tvhari,tvruangan;

        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvnama_matkul = (TextView) view.findViewById(R.id.nama_matkul);
            tvid_matkul = (TextView) view.findViewById(R.id.id_matkul);
            tvhari = (TextView) view.findViewById(R.id.hari);
            tvruangan = (TextView) view.findViewById(R.id.ruangan);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent (context, InsertDataJadwal.class);
                    update.putExtra("update",1);
                    update.putExtra("id_matkul",md.getId_matkul());
                    update.putExtra("nama_matkul",md.getNama_matkul());
                    update.putExtra("hari",md.getHari());
                    update.putExtra("ruangan",md.getRuangan());

                    context.startActivity(update);
                }
            });

        }
    }
}
