package com.navgnss.gankio.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.navgnss.gankio.R;

/**
 * Created by ZhuJinWei on 2017/8/2.
 */

public class BaseActivity extends AppCompatActivity {

    private LinearLayout rootLinearLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        super.setContentView(R.layout.activity_base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = ()

        }

    }
}
