package com.terrence.iread.viewmodel.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.api.BookService;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.model.BookBean;
import com.terrence.iread.view.fragment.IBookSearchInfo;
import com.terrence.iread.viewmodel.BaseViewModel;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class VMBookSearchInfo extends BaseViewModel {
    IBookSearchInfo iBookSearchInfo;

    public VMBookSearchInfo(Context mContext, IBookSearchInfo iBookSearchInfo) {
        super(mContext);
        this.iBookSearchInfo = iBookSearchInfo;
    }


    public void searchBooks(String keyword) {

        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(BookService.class)
                .booksSearch(keyword)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<List<BookBean>>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(List<BookBean> data) {
                        if (iBookSearchInfo != null) {
                            iBookSearchInfo.getSearchBooks(data);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposadle(d);
                    }
                });
    }


}
