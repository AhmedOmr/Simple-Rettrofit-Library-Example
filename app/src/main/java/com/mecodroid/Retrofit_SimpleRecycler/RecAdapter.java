package com.mecodroid.Retrofit_SimpleRecycler;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    viewHolder holders;
    List<Item> getusers;

    public RecAdapter(Context context, List<Item> getusers) {
        this.context = context;
        this.getusers = getusers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listnobeldesign,viewGroup,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        holders= (viewHolder) viewHolder;
        holders.textv1.setText(getusers.get(i).getLogin());
        Picasso.with(context).load(getusers.get(i).getAvatarUrl()).into(holders.imagev1);

    }

    @Override
    public int getItemCount() {
        return getusers != null ? getusers.size() : 0;
    }


    class viewHolder extends RecyclerView.ViewHolder{
        TextView textv1;
        ImageView imagev1;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textv1= itemView.findViewById(R.id.textv2);
            imagev1 = itemView.findViewById(R.id.vimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pi = new Intent(context, DisplayAccount.class);
                    pi.putExtra("view WebView", getusers.get(getAdapterPosition()));
                    context.startActivity(pi);
                }
            });
        }
    }
}
