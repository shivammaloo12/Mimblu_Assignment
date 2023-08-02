package com.shivam.mimblu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class plansAdapter extends RecyclerView.Adapter<plansAdapter.ViewHolder>{

    private Context context;
    private List<plans> list;

    public plansAdapter(Context context, List<plans> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public plansAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_lite, parent, false);
        return new plansAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        plans p = list.get(position);

       // holder.textTitle.setText(p.getTitle());
        holder.textDesc.setText(p.getDesc());
        holder.textDuration.setText(p.getDuration());
        holder.textPrice.setText(p.getPrice());
        holder.textDiscPrice.setText(p.getDiscountedPrice());
        holder.textVideoDesc.setText(p.getVideoDesc());
        holder.textCurrCode.setText(p.getCurrCode());
      //  holder.textdiscCalcPrice.setText(p.getDiscCalcPrice());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle,textDesc,textDuration,textVideoDesc,textPrice,textCurrCode,textDiscPrice,textdiscCalcPrice;

        public ViewHolder(View itemView) {
            super(itemView);
           // textTitle = itemView.findViewById(R.id.);
            textDesc = itemView.findViewById(R.id.description);
            textDuration = itemView.findViewById(R.id.duration);
            textVideoDesc = itemView.findViewById(R.id.video_description);
            textPrice = itemView.findViewById(R.id.final_price);
            textCurrCode = itemView.findViewById(R.id.currency_code);
            textDiscPrice=itemView.findViewById(R.id.discounted_price);
            textdiscCalcPrice=itemView.findViewById(R.id.discounted_price_calculated);
        }
    }
}
