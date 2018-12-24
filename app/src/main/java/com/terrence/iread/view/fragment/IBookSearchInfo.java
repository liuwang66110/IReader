package com.terrence.iread.view.fragment;

import com.terrence.iread.model.BookBean;
import com.terrence.iread.view.base.IBaseLoadView;

import java.util.List;


public interface IBookSearchInfo extends IBaseLoadView {
    void getSearchBooks(List<BookBean> bookBeans);
}
