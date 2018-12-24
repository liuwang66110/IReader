package com.terrence.iread.viewmodel.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.api.BookService;
import com.terrence.iread.api.UserService;
import com.terrence.iread.model.BookBean;
import com.terrence.iread.model.DeleteBookBean;
import com.terrence.iread.utils.ToastUtils;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.db.entity.BookChapterBean;
import com.terrence.iread.db.entity.CollBookBean;
import com.terrence.iread.db.helper.CollBookHelper;
import com.terrence.iread.model.BookChaptersBean;
import com.terrence.iread.view.activity.IBookDetail;
import com.terrence.iread.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


public class VMBookDetailInfo extends BaseViewModel {
    IBookDetail iBookDetail;


    public VMBookDetailInfo(Context mContext, IBookDetail iBookDetail) {
        super(mContext);
        this.iBookDetail = iBookDetail;
    }

    /**
     * 获取书籍信息
     *
     * @param bookid
     */
    public void bookInfo(String bookid) {
        iBookDetail.showLoading();
        RxHttpUtils.getSInstance().addHeaders(tokenMap())
                .createSApi(BookService.class).bookInfo(bookid)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<BookBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        iBookDetail.stopLoading();
                    }

                    @Override
                    protected void onSuccess(BookBean bookBean) {
                        iBookDetail.stopLoading();
                        iBookDetail.getBookInfo(bookBean);
                    }
                });
    }

    /**
     * 添加书籍到书架
     *
     * @param collBookBean
     */
    public void addBookShelf(CollBookBean collBookBean) {

        iBookDetail.showLoading();
        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(BookService.class)
                .bookChapters(collBookBean.get_id())
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<BookChaptersBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        iBookDetail.stopLoading();
                    }

                    @Override
                    protected void onSuccess(BookChaptersBean data) {
                        iBookDetail.stopLoading();
                        List<BookChapterBean> bookChapterList = new ArrayList<>();
                        for (BookChaptersBean.ChatpterBean bean : data.getChapters()) {
                            BookChapterBean chapterBean = new BookChapterBean();
                            chapterBean.setBookId(data.getBook());
                            chapterBean.setLink(bean.getLink());
                            chapterBean.setTitle(bean.getTitle());
//                            chapterBean.setTaskName("下载");
                            chapterBean.setUnreadble(bean.isRead());
                            bookChapterList.add(chapterBean);
                        }
                        collBookBean.setBookChapters(bookChapterList);
                        CollBookHelper.getsInstance().saveBookWithAsync(collBookBean);

                        addBookShelfToServer(collBookBean.get_id());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposadle(d);
                    }
                });
    }

    /**
     * 添加书籍信息到书架
     *
     * @param bookid
     */
    public void addBookShelfToServer(String bookid) {
        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(UserService.class)
                .addBookShelf(bookid).compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        ToastUtils.show(data);
                    }
                });

    }

    /**
     * 删除书籍信息到书架
     *
     * @param mCollBookBean
     */
    public void deleteBookShelfToServer(CollBookBean mCollBookBean) {
        DeleteBookBean bean=new DeleteBookBean();
        bean.setBookid(mCollBookBean.get_id());
        RxHttpUtils.getSInstance().addHeaders(tokenMap()).createSApi(UserService.class)
                .deleteBookShelf(bean).compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        ToastUtils.show(data);
                        CollBookHelper.getsInstance().removeBookInRx(mCollBookBean);
                    }
                });

    }

}
