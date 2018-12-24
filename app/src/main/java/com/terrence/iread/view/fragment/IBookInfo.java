package com.terrence.iread.view.fragment;

import com.terrence.iread.model.BookBean;
import com.terrence.iread.view.base.IBaseDataView;

import java.util.List;


public interface IBookInfo extends IBaseDataView {
    void getBooks(List<BookBean> bookBeans,boolean isLoadMore);
}
