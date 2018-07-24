package com.example.acer.tbcrudmysql.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.tbcrudmysql.Mahasiswa.InsertDataMhs;
import com.example.acer.tbcrudmysql.Model.ModelData;
import com.example.acer.tbcrudmysql.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private List<ModelData>mItems ;
    private Context context;


    public AdapterData (Context context, List<ModelData>items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);

        HolderData holderData = new HolderData(layout);

        return holderData;

    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnama_lengkap.setText(md.getNama_lengkap());
        holder.tvnpm.setText(md.getNpm());
        holder.tvfakultas.setText(md.getFakultas());
        holder.tvprodi.setText(md.getProdi());
        holder.md=md;

    }




    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnama_lengkap,tvnpm,tvfakultas,tvprodi;
        TextView tvnama_matkul,tvid_matkul,tvhari,tvruangan;

        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvnama_lengkap = (TextView) view.findViewById(R.id.nama_lengkap);
            tvnpm = (TextView) view.findViewById(R.id.npm);
            tvfakultas = (TextView) view.findViewById(R.id.fakultas);
            tvprodi = (TextView) view.findViewById(R.id.prodi);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent (context, InsertDataMhs.class);
                    update.putExtra("update",1);
                    update.putExtra("npm",md.getNpm());
                    update.putExtra("nama_lengkap",md.getNama_lengkap());
                    update.putExtra("fakultas",md.getFakultas());
                    update.putExtra("prodi",md.getProdi());

                    context.startActivity(update);
                }
            });

        }
    }
}
