package com.example.obrtniki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

public class RecyclerViewAdapterObrtniki extends RecyclerView.Adapter<RecyclerViewAdapterObrtniki.MyViewHolder> {

    private Context mContext ;
    List<Obrtnik> mData ;
    List<Obrtnik> filteredUserDataList;
    private OnClickListener mOnClickListener;

    String regijeA[] = {"osrednjeslovenska",
            "gorenjska",
            "goriška",
            "obalno-kraška",
            "primorsko-notranjska",
            "jugovzhodna Slovenija",
            "posavska",
            "zasavska",
            "savinjska",
            "koroška",
            "podravska",
            "pomurska"};

    public RecyclerViewAdapterObrtniki(Context mContext, List<Obrtnik> mData, OnClickListener onClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.one_row_obrtnik,parent,false);
        return new MyViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //holder.logo_img.setImageResource(mData.get(position).getLogo());
        holder.naziv_obrtnika.setText(mData.get(position).getCompany_name());
        holder.rating.setText(Double.toString(mData.get(position).getAvg_rating()).substring(0, 3));
        holder.regija.setText(regijeA[mData.get(position).getRegion_id()-1]);

        Glide.with(mContext)
                .load("http://192.168.100.12:8000/storage/documents/" + mData.get(position).getId() + "/0.jpg")
                .error("http://192.168.100.12:8000/storage/documents/0/0.jpg")
                .into(holder.img);

        /*holder.rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tinkara", mData.get(position).getNaziv());
                Intent intent = new Intent(mContext, SelectedObrtListActivity.class);
                intent.putExtra("tip", mData.get(position).getId());
                intent.putExtra("naziv", mData.get(position).getNaziv());
                mContext.startActivity(intent);

                 * začni Activity posameznega obrtnika
                 *
               Intent intent = new Intent(mContext, Book_Activity.class);

                // passing data to the book activity

                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void filterList( ArrayList<Obrtnik> filteredList){
        mData = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img;
        TextView naziv_obrtnika;
        TextView rating;
        TextView regija;
        OnClickListener onClickListener;

        public MyViewHolder(View itemView, OnClickListener onClickListener) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.obrtnik_img);
            naziv_obrtnika = (TextView) itemView.findViewById(R.id.obrtnik_ime);
            rating = (TextView) itemView.findViewById(R.id.rating);
            regija = (TextView) itemView.findViewById(R.id.regija_data);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onRowClick(getAdapterPosition());
        }
    }
    public void Clear(){
        mData.clear();
        if (filteredUserDataList != null)
            filteredUserDataList.clear();
    }

    public interface OnClickListener{
        void onRowClick(int position);
    }
}
