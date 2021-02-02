package com.anpan.wanandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anpan.wanandroid.R;
import com.anpan.wanandroid.adapter.WxAutorsVpadapter;
import com.anpan.wanandroid.entities.WxAuthor;
import com.anpan.wanandroid.vm.WxViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

/**
 * 微信公众号显示
 */
public class WxFragment extends Fragment {

    private WxViewModel wxViewModel;
    private TabLayout mTbWxAuthors;
    private ViewPager2 mVpWxFrg;
    private List<Fragment> fragments;

    private WxFragment() {
    }


    public static WxFragment newInstance() {
        WxFragment fragment = new WxFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wxViewModel = new WxViewModel();
        wxViewModel.getWxAuthorListLiveData().observe(this, new Observer<List<WxAuthor>>() {
            @Override
            public void onChanged(List<WxAuthor> wxAuthors) {
                fragments = new ArrayList<>();
                mTbWxAuthors.removeAllTabs();
                for (int i = 0; i < wxAuthors.size(); i++) {
                    mTbWxAuthors.addTab(mTbWxAuthors.newTab().setText(wxAuthors.get(i).getName()));
                    fragments.add(ArticleInfosFragment.newInstance(wxAuthors.get(i).getId()));
                }
                WxAutorsVpadapter autorsVpadapter = new WxAutorsVpadapter(getFragmentManager(), getLifecycle());
                autorsVpadapter.setFragments(fragments);
                mVpWxFrg.setAdapter(autorsVpadapter);
            }
        });
    }

    private void initView(View rootView) {
        mTbWxAuthors = (TabLayout) rootView.findViewById(R.id.tl_frg_wx);
        mVpWxFrg = (ViewPager2) rootView.findViewById(R.id.vp_frg_wx);
        mVpWxFrg.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTbWxAuthors.setScrollPosition(position, 0, true);
            }
        });


        //添加tablayou联动
        mTbWxAuthors.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mVpWxFrg.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wx, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    public void getData() {
        wxViewModel.getWxAuthorsFromService();
    }
}