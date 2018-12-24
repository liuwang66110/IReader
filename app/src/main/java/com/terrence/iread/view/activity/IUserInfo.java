package com.terrence.iread.view.activity;

import com.terrence.iread.db.entity.UserBean;
import com.terrence.iread.view.base.IBaseLoadView;


public interface IUserInfo extends IBaseLoadView{

    void uploadSuccess(String imageUrl);
    void userInfo(UserBean userBean);

}
