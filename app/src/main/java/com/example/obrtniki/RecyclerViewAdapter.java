package com.example.obrtniki;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<TradeType> mData ;


    public RecyclerViewAdapter(Context mContext, List<TradeType> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_obrtnik,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.img_tip_obrti.setImageResource(mData.get(position).getThumbnail());
        holder.tip_obrti.setText(mData.get(position).getNaziv());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tinkara", mData.get(position).getNaziv());
                Intent intent = new Intent(mContext, SelectedObrtListActivity.class);
                intent.putExtra("tip", mData.get(position).getId());
                intent.putExtra("naziv", mData.get(position).getNaziv());
                mContext.startActivity(intent);
                /*
                * začni Activity posameznega obrtnika
                * */
           /*    Intent intent = new Intent(mContext, Book_Activity.class);

                // passing data to the book activity

                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);*/

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_tip_obrti;
        CardView cardView;
        TextView tip_obrti;

        public MyViewHolder(View itemView) {
            super(itemView);

            img_tip_obrti = (ImageView) itemView.findViewById(R.id.img_tip_obrti);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            tip_obrti = itemView.findViewById(R.id.tip_obrti);

        }
    }
}
