package com.terrence.iread.viewmodel.fragment;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.api.BookService;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.model.BookClassifyBean;
import com.terrence.iread.utils.NetworkUtils;
import com.terrence.iread.view.fragment.IClassifyBook;
import com.terrence.iread.viewmodel.BaseViewModel;

import io.reactivex.disposables.Disposable;


public class VMBookClassify extends BaseViewModel {
    IClassifyBook mIBookClassify;

    public VMBookClassify(Context mContext, IClassifyBook iClassifyBook) {
        super(mContext);
        mIBookClassify = iClassifyBook;
    }

    public void bookClassify() {
        if (!NetworkUtils.isConnected()) {
            if (mIBookClassify != null) {
                mIBookClassify.NetWorkError();
            }
            return;
        }

        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(BookService.class)
       /* RxHttpUtils.createApi(BookService.class)*/
                .bookClassify()
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<BookClassifyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        if (mIBookClassify != null) {
                            mIBookClassify.stopLoading();
                            mIBookClassify.errorData(errorMsg);
                        }
                    }

                    @Override
                    protected void onSuccess(BookClassifyBean data) {
                        if (mIBookClassify != null) {
                            mIBookClassify.stopLoading();
                            if (data == null) {
                                mIBookClassify.emptyData();
                                return;
                            }
                            mIBookClassify.getBookClassify(data);
                        }


                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposadle(d);
                    }
                });
    }


}
