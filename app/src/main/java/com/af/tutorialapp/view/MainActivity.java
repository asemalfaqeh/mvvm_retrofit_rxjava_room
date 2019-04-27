package com.af.tutorialapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.af.tutorialapp.R;
import com.af.tutorialapp.model.DataInfo;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {

    public String greeting = "Welcome to RxJava 2";
    private final static String TAG = "myApp";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Button button;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        // Android Schedulers working with ui
        // schedulers io working with MyDatabase interaction and network interactions

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        Observable observable = Observable.just("JAVA","KOTLIN" , "XML " , "JSON");

        final DataInfo dataInfo = getDataInfo().get(1);

        Observable<DataInfo> myObservable = Observable.just(dataInfo);

        //Skip and distnict operator
        Observable<Integer> digitObservable = Observable.just(1, 2,3,4,4,4,5,6,4);
        digitObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .skip(2)
                .skipLast(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("Integer" , String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                       Log.e("Completed: " , true + "");
                    }
                });


        /*compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                       /* .map(new Function<String, String>() {
                            @Override
                            public String  apply(String s){
                                return s.toLowerCase();
                            }
                            })
                       .flatMap(new Function<DataInfo, Observable<DataInfo>>() {
                           @Override
                           public Observable<DataInfo> apply(DataInfo s){

                               DataInfo dataInfo1 = new DataInfo();
                               dataInfo1.setName(s.getName() + "Replace");

                               return Observable.just(dataInfo1,dataInfo);
                           }
                       })
                       .subscribeWith(getObserver())
        );*/

       // compositeDisposable.add(
         //       myObservable.subscribeWith(getObserver2())
       // );
    }

    private DisposableObserver<DataInfo> getObserver(){

        return new DisposableObserver<DataInfo>() {
            @Override
            public void onNext(DataInfo s) {

                Log.i(TAG, " onNext invoked");

                Log.e("DataInfo: " , s.getName() );

                button.setText(s.getName());
            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " onError invoked");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " onComplete invoked");
            }
        };
    }

    private DisposableObserver<String> getObserver2(){

        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

                Log.i(TAG, " onNext invoked");
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " onError invoked");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " onComplete invoked");
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        //clear observe with current instance
    }

    private ArrayList<DataInfo> getDataInfo(){

        ArrayList<DataInfo> dataInfoArrayList = new ArrayList<>();
        DataInfo dataInfo = new DataInfo(1,"Name","Password","Email");
        dataInfoArrayList.add(dataInfo);

        DataInfo dataInfo1 = new DataInfo(1,"Name","Password","Email");
        dataInfoArrayList.add(dataInfo1);

        DataInfo dataInfo2 = new DataInfo(1,"Name","Password","Email");
        dataInfoArrayList.add(dataInfo2);

        DataInfo dataInfo3 = new DataInfo(1,"Name","Password","Email");
        dataInfoArrayList.add(dataInfo3);

        DataInfo dataInfo4 = new DataInfo(1,"Name","Password","Email");
        dataInfoArrayList.add(dataInfo4);

        return dataInfoArrayList;

    }

    public void secondActivity(View view) {
        DataInfo dataInfo = new DataInfo(1,null,"password" , "Email");
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("Key",dataInfo);
        startActivity(intent);
    }
}
