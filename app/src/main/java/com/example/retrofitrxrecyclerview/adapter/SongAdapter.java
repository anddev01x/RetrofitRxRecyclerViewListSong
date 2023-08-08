package com.example.retrofitrxrecyclerview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitrxrecyclerview.R;
import com.example.retrofitrxrecyclerview.callback.iClickItemListener;
import com.example.retrofitrxrecyclerview.databinding.ItemSongBinding;
import com.example.retrofitrxrecyclerview.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Song> songList;
    private Context context;
    private final iClickItemListener iClickItemListener;


    public SongAdapter(List<Song> songList, Context context, com.example.retrofitrxrecyclerview.callback.iClickItemListener iClickItemListener) {
        this.songList = songList;
        this.context = context;
        this.iClickItemListener = iClickItemListener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongBinding binding = ItemSongBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new SongViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Song song = songList.get(position);
        if (song == null) {
            return;
        }
        holder.binding.tvNameSong.setText(song.getTitle());
        holder.binding.tvNameSing.setText(song.getArtist());
        Glide.with(context).load(song.getImage()).error(R.drawable.ic_music).into(holder.binding.imageView);
        holder.binding.itemSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iClickItemListener!=null){
                    if (position != RecyclerView.NO_POSITION) {
                        iClickItemListener.onClickItemSong(song);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (songList != null) {
            return songList.size();
        }
        return 0;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private ItemSongBinding binding;

        public SongViewHolder(@NonNull ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
