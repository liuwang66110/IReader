package com.terrence.iread.viewmodel.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.api.UserService;
import com.terrence.iread.db.entity.UserBean;
import com.terrence.iread.db.helper.UserHelper;
import com.terrence.iread.utils.SharedPreUtils;
import com.terrence.iread.utils.ToastUtils;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.view.base.BaseActivity;
import com.terrence.iread.viewmodel.BaseViewModel;


public class VMUseLoginInfo extends BaseViewModel {

    public VMUseLoginInfo(Context mContext) {
        super(mContext);
    }


    public void login(String username, String password) {
        RxHttpUtils.getSInstance().addHeaders(tokenMap())
                .createSApi(UserService.class)
                .login(username, password)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<UserBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(UserBean userBean) {
                        ToastUtils.show("登录成功");
                        UserHelper.getsInstance().saveUser(userBean);
                        SharedPreUtils.getInstance().putString("token", userBean.getToken());
                        SharedPreUtils.getInstance().putString("username", userBean.name);
                        ((BaseActivity) mContext).finish();
                    }
                });
    }
}
