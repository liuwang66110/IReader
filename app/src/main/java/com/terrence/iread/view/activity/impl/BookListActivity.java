package com.terrence.iread.view.activity.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.terrence.iread.R;
import com.terrence.iread.view.base.BaseActivity;
import com.terrence.iread.view.base.BaseViewPageAdapter;
import com.terrence.iread.view.fragment.impl.BooksInfoFragment;
import com.terrence.iread.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookListActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.tabStrip)
    NavigationTabStrip mTabStrip;
    private String mTitleName;
    private String mGetder;

    String[] titles = {"热门", "新书", "好评"/*, "完结"*/};
    String[] types = {"hot", "new", "reputation", "over"};
    private List<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinddingView(R.layout.activity_book_list, NO_BINDDING, new BaseViewModel(mContext));
    }


    @Override
    protected void initView() {
        super.initView();
        mTitleName = getIntent().getStringExtra("titleName");
        mGetder = getIntent().getStringExtra("getder");
        initThemeToolBar(mTitleName);

        mFragments = new ArrayList<>();

        for (String type : types) {
            mFragments.add(BooksInfoFragment.newInstance(mTitleName, mGetder, type));
        }

        mViewpager.setAdapter(new BaseViewPageAdapter(getSupportFragmentManager(), titles, mFragments));
        mViewpager.setOffscreenPageLimit(4);
        mTabStrip.setTitles(titles);
        mTabStrip.setViewPager(mViewpager);

    }
}
