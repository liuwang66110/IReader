package com.terrence.iread.viewmodel.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.IReaderApplication;
import com.terrence.iread.api.UserService;
import com.terrence.iread.model.AppUpdateBean;
import com.terrence.iread.utils.ToastUtils;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.view.activity.ISetting;
import com.terrence.iread.viewmodel.BaseViewModel;


public class VMSettingInfo extends BaseViewModel {
    ISetting mISetting;

    public VMSettingInfo(Context mContext, ISetting iSetting) {
        super(mContext);
        mISetting = iSetting;
    }

    /**
     * 版本更新
     */
    public void appUpdate(boolean isTip) {
        mISetting.showLoading();
        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(UserService.class)
                .appUpdate()
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<AppUpdateBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        mISetting.stopLoading();
                    }

                    @Override
                    protected void onSuccess(AppUpdateBean data) {
                        mISetting.stopLoading();
                        if (IReaderApplication.packageInfo.versionCode < data.getVersioncode()) {
                            mISetting.appUpdate(data);
                        } else {
                            if (isTip) {
                                ToastUtils.show("当前是最新版本");
                            }
                        }
                    }
                });
    }
}
