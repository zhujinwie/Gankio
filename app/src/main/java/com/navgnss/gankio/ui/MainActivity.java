package com.navgnss.gankio.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.navgnss.gankio.R;
import com.navgnss.gankio.util.GankApi;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class MainActivity extends AppCompatActivity {

    private static final String TAG="ui.MainActivity";
    
    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();
    private String EndUrl="http://gank.io/api/";
    private Disposable subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Retrofit.Builder builder=new Retrofit.Builder()
                                    .baseUrl(EndUrl)
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit=builder.build();
        GankApi gankApi=retrofit.create(GankApi.class);

         Flowable<String> call= gankApi.getFuLi(1);

        CompositeDisposable composite=new CompositeDisposable();
        composite.add(call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new ResourceSubscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG,"接收到数据："+s);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                Log.d(TAG,"fuck@@@ 出错了！"+t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete！");
            }
        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.dispose();
    }
}
