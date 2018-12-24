package com.terrence.iread.view.fragment;

import com.terrence.iread.db.entity.CollBookBean;
import com.terrence.iread.view.base.IBaseLoadView;

import java.util.List;


public interface IBookShelf extends IBaseLoadView {
    void booksShelfInfo(List<CollBookBean> beans);

    void bookInfo(CollBookBean bean);

    void deleteSuccess();
}
