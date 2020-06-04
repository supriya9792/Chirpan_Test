package com.example.chirpantest;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieAdapter  extends  RecyclerView.Adapter<MovieAdapter.MyView>  {

    private Context mContext;
    private List<Genre> subList;
    private ArrayList<Genre> arraylist;


    public class MyView extends RecyclerView.ViewHolder {
        public TextView moviename;
        ImageView posterImage;
       public CardView cardview;
        public MyView(View view) {
            super(view);
            moviename = (TextView) view.findViewById(R.id.movie_name);
            posterImage = (ImageView) view.findViewById(R.id.poster_image);
            cardview=(CardView)view.findViewById(R.id.cardview);

        }
    }

    public MovieAdapter(Context mContext, List<Genre> albumList) {
        this.mContext = mContext;
        this.subList = albumList;
        this.arraylist = new ArrayList<Genre>();
        this.arraylist.addAll(albumList);
    }

    @Override
    public MovieAdapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list, parent, false);

        return new MovieAdapter.MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.MyView holder, int position) {

        final Genre enq = subList.get(position);

        holder.moviename.setText(enq.getName());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_local_movies_black_24dp);
        Glide.with(mContext).setDefaultRequestOptions(requestOptions).load(enq.getName()).into(holder.posterImage);

        holder.posterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,"Genre Id "+enq.getId(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,MovieDetailActivity.class);
                intent.putExtra("Genre",enq.getId());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return subList.size();
    }

}

