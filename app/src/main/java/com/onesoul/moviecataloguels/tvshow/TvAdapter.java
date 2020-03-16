package com.onesoul.moviecataloguels.tvshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.onesoul.moviecataloguels.R;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private final Context context;
    private ArrayList<Tvshow> listTvshow;

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TvViewHolder(view);
    }

    public TvAdapter(Context context) {
        this.listTvshow = new ArrayList<>();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        holder.tvTitle.setText(getListTv().get(position).getmTitle());
        holder.tvOverview.setText(getListTv().get(position).getmOverview());
        holder.tvRate.setText(getListTv().get(position).getmVote());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + getListTv().get(position).getmPhoto())
                .apply(new RequestOptions()
                .placeholder(R.drawable.ic_image_24px)
                .transform(new RoundedCorners(16)))
                .into(holder.imgPhoto);
    }

    public void setListTv(ArrayList<Tvshow> listTv) {
        this.listTvshow = listTv;
    }

    private ArrayList<Tvshow> getListTv() {
        return listTvshow;
    }

    @Override
    public int getItemCount() {
        return getListTv().size();
    }

    static class TvViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview, tvRate;
        ImageView imgPhoto;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvRate = itemView.findViewById(R.id.tv_rate);
            imgPhoto = itemView.findViewById(R.id.img_poster);
        }
    }
}
