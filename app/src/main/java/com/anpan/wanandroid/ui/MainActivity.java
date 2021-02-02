package com.anpan.wanandroid.ui;

import android.os.Bundle;

import com.anpan.wanandroid.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private IndexFragment indexFragment;
    private WxFragment wxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indexFragment = IndexFragment.newInstance();
        wxFragment = WxFragment.newInstance();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, wxFragment);
        transaction.commit();
    }

}