package com.terrence.iread.view.activity;

import com.terrence.iread.model.BookChaptersBean;
import com.terrence.iread.view.base.IBaseLoadView;


public interface IBookChapters extends IBaseLoadView {
    void bookChapters(BookChaptersBean bookChaptersBean);

    void finishChapters();

    void errorChapters();

}
