package com.navgnss.gankio.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.navgnss.gankio.MyApplication;
import com.navgnss.gankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZhuJinWei on 2017/8/2.
 *
 *你相中的爱情好像，等不住那时间
 * 所以你弃权
 *
 */

public class BaseActivity extends AppCompatActivity {

    /**是否沉浸式状态栏*/
    private boolean isSetStatusBar = true;
    /**是否允许全屏*/
    private boolean isAllowFullScreen = false;
    /**是否允许旋转屏幕*/
    private boolean isAllowScreenRoate = true;

    /**是否输出日志信息*/
    private boolean isDebug;

    private String App_Name;

    Toolbar mToolBar;

    private LinearLayout rootLayout = null; //根布局
    private FrameLayout mContentLayout = null; // 内容布局

    private final String TAG=this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Zog(TAG+"-->onCreate!");
        isDebug= MyApplication.isDebug;
        App_Name=MyApplication.APP_NAME;

        if(rootLayout ==null){

            initRootLayout();
        }
        setSupportActionBar(mToolBar);
        if(mContentLayout != null){

            //内容需要提前清空
            mContentLayout.removeAllViews();
        }

        try{
            /**
             * 全屏设置
             * */
            if(isAllowFullScreen){
                this.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            /**
             * 沉浸式设置
             * */
            if(isSetStatusBar){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                    localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                            | localLayoutParams.flags);

                }

            }
            /**
             * 屏幕旋转性设置
             * */
            if(!isAllowScreenRoate){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

            else{
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

        }catch(Exception e){

            e.printStackTrace();
        }

    }


    /**
     * 初始化根界面
     * */
    private void initRootLayout() {

        rootLayout= new LinearLayout(this);
        rootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        initToolBar();
        mContentLayout=new FrameLayout(this);
        mContentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.addView(mContentLayout);

    }

    /**
     * 初始化toolbar
     * */
    private void initToolBar() {
        Zog(TAG+"--> initToolBar!");
        View view=getLayoutInflater().inflate(R.layout.toolbar_layout,rootLayout);
        mToolBar= (Toolbar) view.findViewById(R.id.toolbar);
        mToolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        mToolBar.setTitle("半岛铁盒");
        setCustomToolBar(mToolBar);
    }

    /**
     * 自定义设计ToolBar 的方法，由子类来实现
     * */
    protected void setCustomToolBar(Toolbar mToolBar) {
        Zog(TAG+"--< setCustomToolBar 方法执行了！");

    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        Zog(TAG+"--> setContentView(int )调用！");
       getLayoutInflater().inflate(layoutResID,mContentLayout);
        super.setContentView(rootLayout);
    }

    @Override
    public void setContentView(View view) {
        Zog(TAG+"--> setContentView(View )调用!");
        mContentLayout.addView(view);
        super.setContentView(rootLayout);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        Zog(TAG+"--> setContentView(view ,params) 调用！");
        mContentLayout.addView(view,params);
        super.setContentView(rootLayout);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void Zog(String str){
        if(isDebug){
            Log.d(App_Name,str);
        }
    }

}
