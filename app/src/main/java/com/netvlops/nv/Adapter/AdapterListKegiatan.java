package com.netvlops.nv.Adapter;

/**
 * Created by Dzaky on 2/23/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netvlops.nv.Detail.DetailKegiatan;
import com.netvlops.nv.Kegiatan;
import com.netvlops.nv.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.netvlops.nv.Server.url.url_img;

/**
 * Created by Dzaky on 2/22/2017.
 */

public class AdapterListKegiatan extends RecyclerView.Adapter<AdapterListKegiatan.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public AdapterListKegiatan(Kegiatan kegiatan, ArrayList<HashMap<String, String>> list_data) {
        this.context = kegiatan;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menugrid, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HashMap<String, String> listItem = list_data.get(position);
        Glide.with(context)
                .load(url_img + list_data.get(position).get("img_keg"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgSiswa);
        holder.tvSiswa.setText(list_data.get(position).get("nama_keg"));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), DetailKegiatan.class);
                a.putExtra("nama_keg", listItem.get("nama_keg"));
                a.putExtra("desc_keg", listItem.get("desc_keg"));
                a.putExtra("img_keg", listItem.get("img_keg"));
//                a.putExtra("gambar2", listItem.get("gambar2"));
//                a.putExtra("gambar3", listItem.get("gambar3"));
                view.getContext().startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSiswa;
        ImageView imgSiswa;
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);

            tvSiswa = (TextView) itemView.findViewById(R.id.tvNamaSiswa);
            imgSiswa = (ImageView) itemView.findViewById(R.id.imgSiswa);
            card = (CardView)itemView.findViewById(R.id.cardView);
        }
    }
}

