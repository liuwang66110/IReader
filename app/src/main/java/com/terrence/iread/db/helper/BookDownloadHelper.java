package com.terrence.iread.db.helper;

import com.terrence.iread.db.entity.DownloadTaskBean;
import com.terrence.iread.db.gen.DaoSession;
import com.terrence.iread.db.gen.DownloadTaskBeanDao;

import java.util.List;

/**
 * 书籍缓存数据库工具类
 */

public class BookDownloadHelper {
    private static volatile BookDownloadHelper sInstance;
    private static DaoSession daoSession;
    private static DownloadTaskBeanDao downloadTaskBeanDao;

    public static BookDownloadHelper getsInstance() {
        if (sInstance == null) {
            synchronized (com.terrence.iread.db.helper.BookChapterHelper.class) {
                if (sInstance == null) {
                    sInstance = new BookDownloadHelper();
                    daoSession = DaoDbHelper.getInstance().getSession();
                    downloadTaskBeanDao = daoSession.getDownloadTaskBeanDao();
                }
            }
        }
        return sInstance;
    }

    /**
     * 缓存列表所有数据
     *
     * @return
     */
    public List<DownloadTaskBean> getBookDownloadList() {
        return downloadTaskBeanDao.loadAll();
    }

    /**
     * 保存缓存列表数据
     *
     * @param taskBean
     */
    public void saveBookDownload(DownloadTaskBean taskBean) {
        com.terrence.iread.db.helper.BookChapterHelper.getsInstance().saveBookChaptersWithAsync(taskBean.getBookChapters());
        downloadTaskBeanDao.insertOrReplace(taskBean);
    }

    /**
     * 删除下载任务
     *
     * @param bookId
     */
    public void removeDownloadTask(String bookId) {
        downloadTaskBeanDao.queryBuilder()
                .where(DownloadTaskBeanDao.Properties.BookId.eq(bookId))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

}
