package com.af.tutorialapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.af.tutorialapp.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

public class SubjectRxJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_rx_java);

        asyncObject2();

    }

    void asyncObjectone(){
        Observable<String> observable = Observable.just("JAVA" , "KOTLIN" , "XML" , "JSON")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> stringAsyncSubject = AsyncSubject.create();
        observable.subscribe(stringAsyncSubject);

        stringAsyncSubject.subscribe(getObserverFirst());
        stringAsyncSubject.subscribe(getObserverSecond());
        stringAsyncSubject.subscribe(getObserverThird());


    }

    void asyncObject2(){

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.subscribe(getObserverFirst());
        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("KOTLIN");

        behaviorSubject.subscribe(getObserverSecond());
        behaviorSubject.onNext("XML");
        behaviorSubject.onNext("KOTLIN");

        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getObserverThird());
        behaviorSubject.onComplete();

    }

    private Observer<String> getObserverFirst(){

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                print("first" , "Subscribe");
            }

            @Override
            public void onNext(String s) {
                print("first" , "next");
            }

            @Override
            public void onError(Throwable e) {
                print("first" , "error");
            }

            @Override
            public void onComplete() {
                print("first" , "complete");
            }
        };

    }

    private Observer<String> getObserverSecond(){

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                print("second" , "Subscribe");
            }

            @Override
            public void onNext(String s) {
                print("second" , "next");
            }

            @Override
            public void onError(Throwable e) {
                print("second" , "error");
            }

            @Override
            public void onComplete() {
                print("second" , "complete");
            }
        };

    }

    private Observer<String> getObserverThird(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                print("Third" , "Subscribe");
            }

            @Override
            public void onNext(String s) {
                print("Third" , "next");
            }

            @Override
            public void onError(Throwable e) {
               print("third" , "error");
            }

            @Override
            public void onComplete() {
             print("third" , "complete");
            }
        };
    }

    private void print(String tag , String msg){
        Log.i(tag,msg);
    }

}
