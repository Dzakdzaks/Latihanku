package com.netvlops.nv.Adapter;

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
import com.netvlops.nv.Detail.DetailTKJ;
import com.netvlops.nv.R;
import com.netvlops.nv.TKJ;

import java.util.ArrayList;
import java.util.HashMap;

import static com.netvlops.nv.Server.url.url_img;

/**
 * Created by Dzaky on 2/22/2017.
 */

public class AdapterListTKJ extends RecyclerView.Adapter<AdapterListTKJ.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public AdapterListTKJ(TKJ tkj, ArrayList<HashMap<String, String>> list_data) {
        this.context = tkj;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HashMap<String, String> listItem = list_data.get(position);
        Glide.with(context)
                .load(url_img + list_data.get(position).get("img_tkj"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgSiswa);
        holder.tvSiswa.setText(list_data.get(position).get("nama_tkj"));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), DetailTKJ.class);
                a.putExtra("nama_tkj", listItem.get("nama_tkj"));
                a.putExtra("desc_tkj", listItem.get("desc_tkj"));
                a.putExtra("img_tkj", listItem.get("img_tkj"));
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

