package com.terrence.iread.view.activity;

import com.terrence.iread.model.BookBean;
import com.terrence.iread.view.base.IBaseLoadView;


public interface IBookDetail extends IBaseLoadView {
    void addBookCallback();
    void getBookInfo(BookBean bookBean);
}
