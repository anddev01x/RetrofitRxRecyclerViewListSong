package com.example.retrofitrxrecyclerview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitrxrecyclerview.R;
import com.example.retrofitrxrecyclerview.adapter.SongAdapter;
import com.example.retrofitrxrecyclerview.callback.iClickItemListener;
import com.example.retrofitrxrecyclerview.databinding.ActivityMainBinding;
import com.example.retrofitrxrecyclerview.model.Song;
import com.example.retrofitrxrecyclerview.viewmodel.SongViewModel;

import java.util.List;

import vn.thanguit.toastperfect.ToastPerfect;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private SongViewModel viewModel;
    private SongAdapter adapter;
    private iClickItemListener iClickItemListener;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        setUpRecyclerView();
        playSong();
    }

    private void playSong() {
        //Click item_song play music
        iClickItemListener = song -> {
            String url = song.getSource();
            stopMedia();
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private void setUpRecyclerView() {
        //setUp RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(decoration);
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.getListMutableLiveData().observe(this, songs -> {
            binding.progressBar.setVisibility(View.GONE);
            adapter = new SongAdapter(songs, MainActivity.this, iClickItemListener);
            binding.recyclerView.setAdapter(adapter);
        });
        viewModel.getToastMessageLiveData().observe(this, message -> {
            if (message.equals("Call API Successfully")) {
                ToastPerfect.makeText(MainActivity.this, ToastPerfect.SUCCESS,
                        message, ToastPerfect.BOTTOM,
                        ToastPerfect.LENGTH_SHORT).show();
            } else {
                binding.progressBar.setVisibility(View.GONE);
                ToastPerfect.makeText(MainActivity.this, ToastPerfect.ERROR,
                        message, ToastPerfect.BOTTOM,
                        ToastPerfect.LENGTH_SHORT).show();
            }
        });
    }

    private void stopMedia() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel.getDisposable() != null) {
            viewModel.setDisposable();
        }
    }

    @Override
    public void onClick(View v) {

    }
}