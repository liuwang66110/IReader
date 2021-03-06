package com.terrence.iread.db.helper;

import com.terrence.iread.db.entity.BookChapterBean;
import com.terrence.iread.db.gen.BookChapterBeanDao;
import com.terrence.iread.db.gen.DaoSession;

import java.util.List;

import io.reactivex.Observable;

/**
 * 书籍目录数据库操作
 */

public class BookChapterHelper {
    private static volatile BookChapterHelper sInstance;
    private static DaoSession daoSession;
    private static BookChapterBeanDao bookChapterBeanDao;

    public static BookChapterHelper getsInstance() {
        if (sInstance == null) {
            synchronized (BookChapterHelper.class) {
                if (sInstance == null) {
                    sInstance = new BookChapterHelper();
                    daoSession = DaoDbHelper.getInstance().getSession();
                    bookChapterBeanDao = daoSession.getBookChapterBeanDao();
                }
            }
        }
        return sInstance;
    }

    /**
     * 保存书籍目录   异步
     *
     * @param bookChapterBeans
     */
    public void saveBookChaptersWithAsync(List<BookChapterBean> bookChapterBeans) {
        daoSession.startAsyncSession()
                .runInTx(
                        () -> {
                            //存储BookChapterBeans
                            daoSession.getBookChapterBeanDao()
                                    .insertOrReplaceInTx(bookChapterBeans);
                        }
                );
    }

    /**
     * 删除书籍目录
     *
     * @param bookId 书籍id
     */
    public void removeBookChapters(String bookId) {
        bookChapterBeanDao.queryBuilder().where(BookChapterBeanDao.Properties.BookId.eq(bookId))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    /**
     * 异步RX查询 书籍目录
     */
    public Observable<List<BookChapterBean>> findBookChaptersInRx(String bookId) {
        return Observable.create(e -> {
            List<BookChapterBean> chapterBeans = daoSession.getBookChapterBeanDao()
                    .queryBuilder()
                    .where(BookChapterBeanDao.Properties.BookId.eq(bookId))
                    .list();
            e.onNext(chapterBeans);
        });
    }

}
