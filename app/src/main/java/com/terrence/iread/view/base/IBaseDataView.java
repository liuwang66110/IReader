package com.terrence.iread.view.base;

/**
 */

public interface IBaseDataView extends IBaseLoadView{

    void emptyData();
    void errorData(String error);
    void NetWorkError();
}
