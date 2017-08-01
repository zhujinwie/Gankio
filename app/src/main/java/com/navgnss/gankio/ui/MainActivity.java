package com.navgnss.gankio.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import com.navgnss.gankio.R;
import com.navgnss.gankio.adapter.FluidAdapter;
import com.navgnss.gankio.bean.FuLiData;
import com.navgnss.gankio.util.GankApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.navgnss.gankio.bean.MyEndUrl.URL_GANK;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="ui.MainActivity";
    public RecyclerView mRecyclerView;
    public FluidAdapter mFluidAdapter;
    List<String> datas;
    int page=1;

    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();


    private CompositeDisposable composite;

    private static  Retrofit retrofit;
    private static  GankApi gankApi;
    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_GANK)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
         gankApi=retrofit.create(GankApi.class);
    }

    private Map<String,String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        datas=new ArrayList<>();
        composite=new CompositeDisposable();
        mFluidAdapter=new FluidAdapter(this,datas);
        mRecyclerView=(RecyclerView) findViewById(R.id.main_rv_fuli);

        initRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getImageUrl();
            }
        });

    }
    /**
     * 设置recyclerView
     * */
    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mFluidAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //mRecyclerView.addItemDecoration();

        mRecyclerView.addOnScrollListener(getMyScrollListener(layoutManager));
    }
    /**
     * RecyclerView的滑动刷新
     * */
    private RecyclerView.OnScrollListener getMyScrollListener(RecyclerView.LayoutManager layoutManager) {

        int lastVisibleItem=((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();

        return null;

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
        composite.dispose();
    }


    /**
     *
     * 获取图片url
     * */
    public void getImageUrl(){

        composite.add(gankApi.getFuLi(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new ResourceSubscriber<FuLiData>() {
                            @Override
                            public void onNext(FuLiData fuLiData) {
                                if(!fuLiData.isError()){
                                    for(FuLiData.Result result:fuLiData.getResults()){
                                        datas.add(result.getUrl());
                                        datas.add(result.getDesc());
                                    }
                                   // Picasso.with(MainActivity.this).load(result.getUrl()).into(mImageView);
                                }
                            mFluidAdapter.addAll(datas,false);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }));



    }


}
