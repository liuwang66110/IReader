package com.terrence.iread.view.fragment;

import com.terrence.iread.model.BookClassifyBean;
import com.terrence.iread.view.base.IBaseDataView;


public interface IClassifyBook extends IBaseDataView {

    void getBookClassify(BookClassifyBean bookClassifyBean);

}
