package com.terrence.iread.viewmodel.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.api.UserService;
import com.terrence.iread.utils.ToastUtils;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.view.activity.IFeedBack;
import com.terrence.iread.viewmodel.BaseViewModel;



public class VMFeedBackInfo extends BaseViewModel {
    IFeedBack iFeedBack;

    public VMFeedBackInfo(Context mContext, IFeedBack iFeedBack) {
        super(mContext);
        this.iFeedBack = iFeedBack;
    }

    /**
     * 提交反馈
     *
     * @param qq
     * @param feedback
     */
    public void commitFeedBack(String qq, String feedback) {
        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(UserService.class)
                .userFeddBack(qq, feedback)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        ToastUtils.show("提交反馈成功");
                        iFeedBack.feedBackSuccess();
                    }
                });
    }
}
