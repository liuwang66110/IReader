package com.terrence.iread.view.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.terrence.iread.R;
import com.terrence.iread.view.activity.impl.MainActivity;
import com.terrence.iread.view.base.BaseFragment;
import com.terrence.iread.view.base.BaseViewPageAdapter;
import com.terrence.iread.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class BookClassifyFragment extends BaseFragment {

    @BindView(R.id.nts_classify)
    NavigationTabStrip mNtsClassify;
    @BindView(R.id.vp_classify)
    ViewPager mVpClassify;

    String[] titles = {"男生", "女生", "出版"};
    private List<Fragment> mFragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = setContentView(container, R.layout.fragment_book_classify, new BaseViewModel(mContext));
        return view;
    }

    public static BookClassifyFragment newInstance() {
        BookClassifyFragment fragment = new BookClassifyFragment();
        return fragment;
    }


    @Override
    public void initView() {
        super.initView();
        for (int i = 0; i < titles.length; i++) {
            mFragments.add(ClassifyFragment.newInstance(titles[i]));
        }
        mVpClassify.setAdapter(new BaseViewPageAdapter(getActivity().getSupportFragmentManager(), titles, mFragments));
        mVpClassify.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((MainActivity) getActivity()).setLeftSlide(position == 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpClassify.setOffscreenPageLimit(4);
        mNtsClassify.setTitles(titles);
        mNtsClassify.setViewPager(mVpClassify);
    }

}
