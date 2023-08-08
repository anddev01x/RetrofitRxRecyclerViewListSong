package com.example.retrofitrxrecyclerview.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitrxrecyclerview.api.Api;
import com.example.retrofitrxrecyclerview.model.ListSong;
import com.example.retrofitrxrecyclerview.model.Song;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SongViewModel extends ViewModel {
    private MutableLiveData<List<Song>> mListMutableLiveData;
    private MutableLiveData<String> toastMessageLiveData = new MutableLiveData<>();

    private List<Song> songList;
    private Disposable mDisposable;

    public MutableLiveData<List<Song>> getListMutableLiveData() {
        return mListMutableLiveData;
    }

    public SongViewModel() {
        mListMutableLiveData = new MutableLiveData<>();
        initViews();
    }

    public LiveData<String> getToastMessageLiveData() {
        return toastMessageLiveData;
    }

    private void initViews() {
        songList = new ArrayList<>();
        callApiGetData();
    }

    private void callApiGetData() {
        Api.apiService.getListSong().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListSong>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                        Log.i("TrungChieu", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull ListSong listSong) {
                        Log.i("TrungChieu", "onNext: ");
                        if (listSong != null) {
                            songList = listSong.getSongs();
                            mListMutableLiveData.setValue(songList);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("TrungChieu", "onError: " + e.getMessage());
                        toastMessageLiveData.setValue("Call API Error");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("TrungChieu", "onComplete: ");
                        toastMessageLiveData.setValue("Call API Successfully");
                    }
                });
    }

    public Disposable getDisposable() {
        return mDisposable;
    }

    public void setDisposable() {
        mDisposable.dispose();
    }
}
