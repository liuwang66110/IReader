package com.terrence.iread.viewmodel.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.api.UserService;
import com.terrence.iread.utils.MD5Utils;
import com.terrence.iread.utils.ToastUtils;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.view.activity.IUserRegister;
import com.terrence.iread.viewmodel.BaseViewModel;


public class VMUserRegisterInfo extends BaseViewModel {
    IUserRegister userRegister;

    public VMUserRegisterInfo(Context mContext, IUserRegister userRegister) {
        super(mContext);
        this.userRegister = userRegister;
    }


    public void register(String username, String password) {
        //对密码进行md5加密
        String md5Pass = MD5Utils.encrypt(password);
        RxHttpUtils.getSInstance().addHeaders(tokenMap())
                .createSApi(UserService.class)
                .register(username, md5Pass)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        ToastUtils.show(data);
                        if (data.equals("注册成功")) {
                            userRegister.registerSuccess();
                        }
                    }
                });
    }
}
