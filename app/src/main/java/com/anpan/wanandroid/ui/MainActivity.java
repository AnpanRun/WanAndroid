package com.anpan.wanandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.anpan.wanandroid.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private IndexFragment indexFragment;
    private WxFragment wxFragment;
    private TextView mTvIndex;
    private TextView mTvWx;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        indexFragment = IndexFragment.newInstance();
        wxFragment = WxFragment.newInstance();
        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, indexFragment);
        transaction.commit();

        mTvIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_main, indexFragment);
                transaction.commit();
            }
        });

        mTvWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_main, wxFragment);
                transaction.commit();
            }
        });

    }

    private void initView() {
        mTvIndex = (TextView)findViewById(R.id.tv_index);
        mTvWx = (TextView)findViewById(R.id.tv_gzh);
    }

}