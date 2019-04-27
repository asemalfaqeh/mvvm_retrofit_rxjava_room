package com.af.tutorialapp.retrofit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.af.tutorialapp.R;
import com.af.tutorialapp.retrofit.model.albums;
import com.af.tutorialapp.retrofit.service.ApiEndPoints;
import com.af.tutorialapp.retrofit.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AlbumsActivity extends AppCompatActivity {

    private Observable<List<albums>> observable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ArrayList<albums> albumsArrayList = new ArrayList<>();
    //Created By AF
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        loadDataRx();
    }

    private void loadData(){

        ApiEndPoints getAlbumsDataService = RetrofitInstance.getRetrofitInstance();
        Call<List<albums>> listCall = getAlbumsDataService.getAlbums();

        listCall.enqueue(new Callback<List<albums>>() {
            @Override
            public void onResponse(Call<List<albums>> call, Response<List<albums>> response) {

                List<albums> list = new ArrayList<>();

                for(int i = 0 ; i < response.body().size() ; i ++) {

                    albums albums = new albums();

                    albums.setTitle(response.body().get(i).getTitle());
                    albums.setUserId(response.body().get(i).getUserId());
                    list.add(albums);

                }

                for (int i = 0 ; i < list.size() ; i++){
                    Log.e("List : " , String.valueOf(list.get(i).getTitle()));
                }

            }

            @Override
            public void onFailure(Call<List<albums>> call, Throwable t) {

                Toast.makeText(AlbumsActivity.this, "something error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadDataRx(){

        ApiEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance();
        observable = apiEndPoints.getAlbumsRx();

        compositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<albums>, Observable<albums>>)
                        albums -> Observable.fromArray(albums.toArray(new albums[0])))
                .filter(albums -> albums.getUserId() == 1)
        .subscribeWith(getObserver()));
    }

    private DisposableObserver<albums> getObserver(){

        return new DisposableObserver<albums>() {
            @Override
            public void onNext(albums albums) {
                Log.e("Data: " , String.valueOf(albums.getTitle()));
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(AlbumsActivity.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                for (int i = 0 ; i < albumsArrayList.size() ; i++){
                    Log.e("Data: " , albumsArrayList.get(i).getTitle());
                }
            }
        };

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        compositeDisposable.clear();
    }

}
