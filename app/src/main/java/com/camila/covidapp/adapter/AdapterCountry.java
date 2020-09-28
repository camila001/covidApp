package com.camila.covidapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.camila.covidapp.R;
import com.camila.covidapp.model.Country;

import java.util.List;

public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.CountryHolder> {

    public Activity activity;
    public List<Country> list;
    public int item_country;

    public AdapterCountry(Activity activity, List<Country> list, int item_country) {
        this.activity = activity;
        this.list = list;
        this.item_country = item_country;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_country,parent,false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        Country country = list.get(position);
        holder.item_name.setText(country.name);
        holder.item_code.setText(country.code);
        holder.item_total.setText(country.totalCases);
        holder.item_deaths.setText(country.totalDeaths);
        holder.item_reco.setText(country.totalRecovered);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class CountryHolder extends RecyclerView.ViewHolder{
        TextView item_name, item_code, item_total, item_reco, item_deaths;
        RelativeLayout item_card;

        public CountryHolder(View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_code = itemView.findViewById(R.id.item_code);
            item_total = itemView.findViewById(R.id.item_total);
            item_reco = itemView.findViewById(R.id.item_reco);
            item_deaths = itemView.findViewById(R.id.item_deaths);
            item_card = itemView.findViewById(R.id.item_card);
        }
    }
}
